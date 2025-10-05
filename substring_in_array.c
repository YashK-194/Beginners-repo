#include <stdio.h>
#include <string.h>

int main() {
    char str[100], substr[50];

    // Input main string
    printf("Enter the main string: ");
    fgets(str, sizeof(str), stdin);
    str[strcspn(str, "\n")] = '\0'; // remove trailing newline

    // Input substring
    printf("Enter the substring to search: ");
    fgets(substr, sizeof(substr), stdin);
    substr[strcspn(substr, "\n")] = '\0'; // remove newline

    // Use strstr() to find substring
    char *pos = strstr(str, substr);

    if (pos != NULL) {
        printf("Substring found at position: %ld\n", pos - str + 1); // 1-based index
    } else {
        printf("Substring not found.\n");
    }

    return 0;
}
