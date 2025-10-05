#include <stdio.h>

int main() {
    int arr[100], n, pos, value;

    // Step 1: Take array size input
    printf("Enter number of elements in array: ");
    scanf("%d", &n);

    // Step 2: Take array elements input
    printf("Enter %d elements:\n", n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }

    // Step 3: Take position and value input
    printf("Enter the position where you want to insert (1 to %d): ", n + 1);
    scanf("%d", &pos);

    printf("Enter the value to insert: ");
    scanf("%d", &value);

    // Step 4: Shift elements to the right to make space
    for (int i = n - 1; i >= pos - 1; i--) {
        arr[i + 1] = arr[i];
    }

    // Step 5: Insert the new element
    arr[pos - 1] = value;

    // Step 6: Increase the size of array
    n++;

    // Step 7: Display the updated array
    printf("\nArray after insertion:\n");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }

    printf("\n");
    return 0;
}
