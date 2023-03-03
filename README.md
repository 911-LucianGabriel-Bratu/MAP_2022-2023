# MAP_2022-2023
Toy-Language Interpreter using Java

Assignment1(Simple Java Program) -> 8. Intr-un acvariu traiesc pesti si broaste testoase.
Sa se afiseze toate vietuitoarele din acvariu care sunt 
mai batrine de 1 an.

Assignment2 -> Toy Language Interpreter as a console application.
Assignment7 -> Toy Language Interpreter with a working GUI.

The purpose of this project was to experiment with how variables are created,
where they are kept (Symbol Table), how operations are executed (Execution Stack)
and how Reference Type variables are stored (Heap) when running a program in
a Toy-Language manner.

A program in the toy language has the form CompoundStatement(Statement, CompoundStatement(Statement, ...)).
When executing such compound statement, the first attribute (Statement), followed by the next Compound Statement
are pushed on the Execution Stack. When there are no more compound statements left, each statement is popped from
the stack and the corresponding operations are done. The program ends when the execution stack is empty.
