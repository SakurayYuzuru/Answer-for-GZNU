#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>

typedef struct {
    int *elements;
    int size;    
    int capacity;
} Set;

Set createSet(int capacity) {
    Set newSet;
    newSet.elements = (int *)malloc(capacity * sizeof(int));

    if (newSet.elements == NULL) {
        printf("Memory allocation failed!\n");
        exit(1);
    }

    newSet.size = 0;
    newSet.capacity = capacity;
    return newSet;
}

_Bool isElementInSet(Set *set, int element) {
    for (int i = 0; i < set->size; i++) {
        if (set->elements[i] == element) {
            return true; 
        }
    }
    return false;
}

void addElement(Set *set, int element) {
    if (!isElementInSet(set, element)) {
        if (set->size == set->capacity) {
            printf("Set is full, cannot add element.\n");
            return;
        }
        set->elements[set->size] = element;
        set->size++;
    }
}

Set unionSets(Set *set1, Set *set2) {
    Set resultSet = createSet(set1->size + set2->size);

    for (int i = 0; i < set1->size; i++) {
        addElement(&resultSet, set1->elements[i]);
    }
    for (int i = 0; i < set2->size; i++) {
        addElement(&resultSet, set2->elements[i]);
    }

    return resultSet;
}

Set differenceSets(Set *set1, Set *set2) {
    Set resultSet = createSet(set1->size);

    for (int i = 0; i < set1->size; i++) {
        int element = set1->elements[i];
        if (!isElementInSet(set2, element)) {
            addElement(&resultSet, element);
        }
    }

    return resultSet;
}

Set intersectionSets(Set *set1, Set *set2) {
    Set resultSet = createSet(set1->size < set2->size ? set1->size : set2->size);

    for (int i = 0; i < set1->size; i++) {
        int element = set1->elements[i];
        if (isElementInSet(set2, element)) {
            addElement(&resultSet, element);
        }
    }

    return resultSet;
}


void printSet(Set *set) {
    printf("{ ");
    for (int i = 0; i < set->size; i++) {
        printf("%d ", set->elements[i]);
    }
    printf("}\n");
}

int main() {
    Set set1 = createSet(10);
    Set set2 = createSet(10);

    addElement(&set1, 1);
    addElement(&set1, 2);
    addElement(&set1, 3);

    addElement(&set2, 3);
    addElement(&set2, 4);
    addElement(&set2, 5);

    printf("Set 1: ");
    printSet(&set1);

    printf("Set 2: ");
    printSet(&set2);

    Set unionSet = unionSets(&set1, &set2);
    printf("Union of Set 1 and Set 2: ");
    printSet(&unionSet);

    Set differenceSet = differenceSets(&set1, &set2);
    printf("Difference of Set1 \\ Set2: ");
    printSet(&differenceSet);

    Set intersectionSet = intersectionSets(&set1, &set2);
    printf("Intersection of Set 1 and Set 2: ");
    printSet(&intersectionSet);


    free(set1.elements);
    free(set2.elements);
    free(unionSet.elements);
    free(differenceSet.elements);
    free(intersectionSet.elements);

    time_t currentTime;
    time(&currentTime);
    struct tm *localTime = localtime(&currentTime);
    printf("Current date and time: %d-%02d-%02d %02d:%02d:%02d\n",
           localTime->tm_year + 1900,  
           localTime->tm_mon + 1,      
           localTime->tm_mday,         
           localTime->tm_hour,         
           localTime->tm_min,          
           localTime->tm_sec);

    return 0;
}
