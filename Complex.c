#include <stdio.h>

typedef struct Complex{
    float real;
    float imag;  
} Complex;

Complex operator+(Complex a) const{
    return Complex(this-> real + a.real, this-> imag + a.imag);
}

int main(){
    struct Complex x, y;
    x.real = 1.f, x.imag = 1.f;
    y.real = 1.f, y.imag = 2.f;
    x = x + y;
    printf("%f + %fi", x.real, x.imag);

    return 0;
}