#!/usr/bin/env python3
"""
Simple Console ATM Application
Features:
- Multiple users stored in a JSON file (persistence)
- PIN authentication
- Balance inquiry
- Cash withdraw (with balance & daily limit checks)
- Cash deposit
- Mini-statement (last N transactions)
- Change PIN
- Admin function to create sample accounts

How to run:
1. Save as ATM_App.py
2. Run: python3 ATM_App.py

This is a single-file demo intended for learning. For production you'd use a secure database,
proper hashing for PINs, and stronger input validation.
"""

import json
import os
import getpass
import datetime
from typing import Dict, List

DATA_FILE = "atm_data.json"
DAILY_WITHDRAW_LIMIT = 50000  # currency units per day


def now_iso():
    return datetime.datetime.now().isoformat(sep=' ', timespec='seconds')


class Transaction:
    def __init__(self, ttype: str, amount: float, note: str = ""):
        self.time = now_iso()
        self.type = ttype  # 'withdraw', 'deposit', 'pin_change'
        self.amount = amount
        self.note = note

    def to_dict(self):
        return {"time": self.time, "type": self.type, "amount": self.amount, "note": self.note}


class Account:
    def __init__(self, acc_no: str, holder: str, pin: str, balance: float = 0.0, txs: List[Dict] = None):
        self.acc_no = acc_no
        self.holder = holder
        self.pin = pin  # NOTE: Insecure to store plain PINs — only for demo
        self.balance = balance
        self.txs = txs or []

    def deposit(self, amount: float):
        if amount <= 0:
            raise ValueError("Deposit amount must be positive")
        self.balance += amount
        self.txs.append(Transaction("deposit", amount).to_dict())

    def withdraw(self, amount: float):
        if amount <= 0:
            raise ValueError("Withdraw amount must be positive")
        if amount > self.balance:
            raise ValueError("Insufficient balance")
        # Check daily withdraw limit
        today = datetime.datetime.now().date()
        withdrawn_today = sum(t['amount'] for t in self.txs
                              if t['type'] == 'withdraw' and
                              datetime.datetime.fromisoformat(t['time']).date() == today)
        if withdrawn_today + amount > DAILY_WITHDRAW_LIMIT:
            raise ValueError(f"Daily withdraw limit exceeded. You have already withdrawn {withdrawn_today}")
        self.balance -= amount
        self.txs.append(Transaction("withdraw", amount).to_dict())

    def change_pin(self, new_pin: str):
        self.pin = new_pin
        self.txs.append(Transaction("pin_change", 0, note="PIN changed").to_dict())

    def mini_statement(self, n: int = 5):
        return self.txs[-n:][::-1]

    def to_dict(self):
        return {
            "acc_no": self.acc_no,
            "holder": self.holder,
            "pin": self.pin,
            "balance": self.balance,
            "txs": self.txs,
        }

    @staticmethod
    def from_dict(d):
        return Account(d['acc_no'], d['holder'], d['pin'], d.get('balance', 0.0), d.get('txs', []))


class Bank:
    def __init__(self, data_file=DATA_FILE):
        self.data_file = data_file
        self.accounts: Dict[str, Account] = {}
        self.load()

    def load(self):
        if os.path.exists(self.data_file):
            try:
                with open(self.data_file, 'r') as f:
                    raw = json.load(f)
                    self.accounts = {k: Account.from_dict(v) for k, v in raw.items()}
            except Exception:
                print("Warning: failed to load data file, starting with empty bank")
                self.accounts = {}
        else:
            self.accounts = {}

    def save(self):
        with open(self.data_file, 'w') as f:
            json.dump({k: v.to_dict() for k, v in self.accounts.items()}, f, indent=2)

    def authenticate(self, acc_no: str, pin: str) -> Account:
        acc = self.accounts.get(acc_no)
        if not acc:
            raise ValueError("Account not found")
        if acc.pin != pin:
            raise ValueError("Invalid PIN")
        return acc

    def create_account(self, acc_no: str, holder: str, pin: str, initial: float = 0.0):
        if acc_no in self.accounts:
            raise ValueError("Account number already exists")
        acc = Account(acc_no, holder, pin, initial, [])
        self.accounts[acc_no] = acc
        self.save()
        return acc


def clear_screen():
    os.system('cls' if os.name == 'nt' else 'clear')


def input_pin(prompt="Enter PIN: "):
    # Use getpass so PIN isn't echoed
    return getpass.getpass(prompt)


def main_menu():
    print("\n=== Welcome to PyATM ===")
    print("1. Login")
    print("2. Create sample accounts (admin)")
    print("3. Exit")
    return input("Choose: ")


def user_menu(name):
    clear_screen()
    print(f"\nHello, {name} — What would you like to do?")
    print("1. Balance enquiry")
    print("2. Withdraw")
    print("3. Deposit")
    print("4. Mini-statement")
    print("5. Change PIN")
    print("6. Logout")
    return input("Choose: ")


def create_sample_accounts(bank: Bank):
    # Creates a few demo accounts if not present
    samples = [
        ("1001", "Alice", "1234", 25000.0),
        ("1002", "Bob", "2345", 15000.0),
        ("1003", "Charlie", "3456", 5000.0),
    ]
    created = 0
    for acc_no, holder, pin, bal in samples:
        if acc_no not in bank.accounts:
            bank.create_account(acc_no, holder, pin, bal)
            created += 1
    print(f"Created {created} sample accounts (or they already existed)")


def run():
    bank = Bank()
    while True:
        clear_screen()
        choice = main_menu()
        if choice == '1':
            acc_no = input("Account number: ")
            pin = input_pin()
            try:
                acc = bank.authenticate(acc_no, pin)
            except ValueError as e:
                print(f"Login failed: {e}")
                input("Press Enter to continue...")
                continue

            # Logged in
            while True:
                c = user_menu(acc.holder)
                if c == '1':
                    print(f"\nCurrent balance: {acc.balance:.2f}")
                    input("Press Enter to continue...")
                elif c == '2':
                    amt_s = input("Enter amount to withdraw: ")
                    try:
                        amt = float(amt_s)
                        acc.withdraw(amt)
                        bank.save()
                        print(f"Withdrawn {amt:.2f}. New balance: {acc.balance:.2f}")
                    except Exception as e:
                        print(f"Withdraw failed: {e}")
                    input("Press Enter to continue...")
                elif c == '3':
                    amt_s = input("Enter amount to deposit: ")
                    try:
                        amt = float(amt_s)
                        acc.deposit(amt)
                        bank.save()
                        print(f"Deposited {amt:.2f}. New balance: {acc.balance:.2f}")
                    except Exception as e:
                        print(f"Deposit failed: {e}")
                    input("Press Enter to continue...")
                elif c == '4':
                    n = input("How many recent transactions? (default 5): ")
                    try:
                        n = int(n) if n.strip() else 5
                    except Exception:
                        n = 5
                    stm = acc.mini_statement(n)
                    if not stm:
                        print("No transactions yet.")
                    else:
                        print("\nTime                 | Type     | Amount    | Note")
                        print("-" * 60)
                        for t in stm:
                            print(f"{t['time']:<20} | {t['type']:<8} | {t['amount']:<8} | {t.get('note','')}")
                    input("Press Enter to continue...")
                elif c == '5':
                    current = input_pin("Enter current PIN: ")
                    if current != acc.pin:
                        print("Incorrect current PIN")
                        input("Press Enter to continue...")
                        continue
                    new_pin = input_pin("Enter new PIN: ")
                    new_pin2 = input_pin("Confirm new PIN: ")
                    if new_pin != new_pin2:
                        print("PINs do not match")
                        input("Press Enter to continue...")
                        continue
                    acc.change_pin(new_pin)
                    bank.save()
                    print("PIN changed successfully")
                    input("Press Enter to continue...")
                elif c == '6':
                    print("Logging out...")
                    input("Press Enter to continue...")
                    break
                else:
                    print("Invalid choice")
                    input("Press Enter to continue...")

        elif choice == '2':
            create_sample_accounts(bank)
            input("Press Enter to continue...")
        elif choice == '3':
            print("Goodbye!")
            break
        else:
            print("Invalid choice")
            input("Press Enter to continue...")


if __name__ == '__main__':
    run()
