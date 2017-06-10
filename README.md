# cryptopals

My solutions to the [Matasano Cryptopals challenges](https://cryptopals.com/).

## Building and Running the Programs

For each challenge, I've created a program that prints the solution to the terminal. I've tested
all of the solutions in Ubuntu 16.04; they may or may not work on other operating systems. I'll
note any non-standard libraries that I use.

### Haskell

In `haskell/setX/`, run `ghc --make sXcYmain.hs` to build the program for Set `X` Challenge `Y`.
This will generate an executable `sXcYmain` that can be run. All logic for Set `X` Challenge `Y`
can be found in `haskell/setX/SetXChallengeY.hs`.

**Disclaimer:** I wrote this code in the process of learning Haskell. It is not very nice. Once I
know the language better, I hope to come back and rewrite it from scratch.

### Java

In `java/`, run the `build.bash` script to build all programs. In `java/build/`, run
`java SXCYMain` to run the program for Set `X` Challenge `Y`. All logic for Set `X` Challenge `Y`
can be found in `java/src/setX/SetXChallengeY.java`.

## Style

In each code file, I note the time of its creation at the top, with reference to one of Spring,
Fall, or Winter (not Summer). These refer not to the actual seasons, but to the university terms
here in Canada: Spring runs from May to August, Fall from September to December, and Winter from
January to April.

If you have any pointers you'd like to share about my code, feel free to
[open an issue](https://github.com/jdw1996/cryptopals/issues/new) or email me at
[jdwinters96@gmail.com](mailto:jdwinters96@gmail.com).

## Challenges Completed

### Set 1

**Challenge** | **1** | **2** | **3** | **4** | **5** | **6** | **7** | **8**
--------------|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:|:-----:
Haskell|:white_check_mark:|:white_check_mark:|:white_check_mark:|||||
Java|:white_check_mark:|:white_check_mark:|:white_check_mark:|:white_check_mark:|:white_check_mark:|:white_check_mark:|:white_check_mark:|:white_check_mark:
