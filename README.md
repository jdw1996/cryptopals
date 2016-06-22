# cryptopals
My solutions to the [Matasano Cryptopals challenges](https://cryptopals.com/).

## Format

I started out doing these challenges in Haskell, but found that to be quite
difficult, given my inexperience with functional programming. I'm still
planning on learning Haskell, but until I'm more comfortable with it, I'll be
switching to completing these problems in Java. I'm fairly new to Java as well,
but having programmed in C and C++, I'm expecting less of a learning curve.

In case anyone finds them interesting, I'll leave what I had written in Haskell
in the `haskell` subdirectory; my Java solutions will be in the `java`
subdirectory.

Within each language-specific subdirectory, each set of problems has its own
subdirectory (or will, once I have started on it). For a challenge `Y` in set
`X`, the file `SetXChallengeY.Z` is a module containing the functions that
solve the problem (and sometimes other functions, for the sake of completeness)
and the file `SXCYMain.Z` is a compilable program that displays the answer to
the specific problem given. `Z` is the file extension for the appropriate
language.

## Style

I'm using these challenges to learn more about cryptography, but also to learn
new languages and language features. If you have any pointers you'd like to
share about my code, feel free to open an issue or email me at
[jdwinters96@gmail.com](mailto:jdwinters96@gmail.com).
