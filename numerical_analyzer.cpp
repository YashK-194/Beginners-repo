/*
This C++ program reads a series of integers from the user, separated by spaces.
It validates the input to ensure only integers are entered and prompts the user 
again if the input is invalid. Once valid input is received, the program calculates 
and displays the following numerical analysis results:
   - Largest number
   - Smallest number
   - Sum of all numbers
   - Average of the numbers
   - Range (difference between largest and smallest)

    Input          : Integers entered by the user separated by spaces
    Output         : Summary of numerical analysis including largest, smallest, sum, average, and range
    Features       : 
        - Input validation
        - Recursive prompting for invalid input
        - Uses vector to store numbers dynamically
        - Uses stringstream for parsing input
*/

#include <iostream>
#include <vector>
#include <sstream>
using namespace std;

vector<int> getNumber(){
    cout << "Enter integers separated by spaces (press enter to finish): " << endl;
    vector<int> numbers;
    string s;
    getline(cin, s);
    stringstream ss(s);
    int num;
    while(ss >> num){
        numbers.push_back(num);
    }
    // Check if any invalid input remains in the stream
    char leftover;
    ss.clear();
    if(ss >> leftover){ 
        cout << "Invalid input detected! Please enter only integers.\n";
        return getNumber();
    }
    if(numbers.size() == 0){
        cout << "No integers entered! Please enter at least one integer.\n";
        return getNumber();
    }
    return numbers;
}

void displayResult(vector<int>& nums) {
    int largest = nums[0];
    int smallest = nums[0];
    int sum = 0;
    for (int n : nums) {
        if (n > largest) largest = n;
        if (n < smallest) smallest = n;
        sum += n;
    }

    double average = static_cast<double>(sum) / nums.size();
    int range = largest - smallest;

    cout << "Numerical Analysis Results:\n";
    cout << "------------------------------\n";
    cout << "Numbers Entered : ";
    for (int n : nums) cout << n << " ";
    cout << "\nLargest Number  : " << largest;
    cout << "\nSmallest Number : " << smallest;
    cout << "\nSum             : " << sum;
    cout << "\nAverage         : " << average;
    cout << "\nRange           : " << range << "\n";
}

int main() {
    cout << "== Welcome to numerical analyzer =="<<endl;
    vector<int> numbers = getNumber();
    displayResult(numbers);
    return 0;
}