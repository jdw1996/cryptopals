------------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 3 Functions
-- Spring 2016
------------------------------------------


module Set1Challenge3 (crackSingleCharXOR) where


import qualified Data.Char as Char
import qualified Data.List as List
import qualified Data.Map as Map

import qualified Set1Challenge1 as S1C1
import qualified Set1Challenge2 as S1C2


-- English letter frequency, based on the complete Sherlock Holmes stories.
englFreq = [ (' ', 0.180006173221307)
           , ('a', 6.690385461794901e-2)
           , ('b', 1.1937384532226097e-2)
           , ('c', 2.0288563734027987e-2)
           , ('d', 3.534512818995832e-2)
           , ('e', 0.10113744441187518)
           , ('f', 1.7145180958152074e-2)
           , ('g', 1.4914932950629468e-2)
           , ('h', 5.482513738106614e-2)
           , ('i', 5.662003285014281e-2)
           , ('j', 8.354466008886929e-4)
           , ('k', 6.590048205507912e-3)
           , ('l', 3.249636284915976e-2)
           , ('m', 2.2665821658445166e-2)
           , ('n', 5.430133985196819e-2)
           , ('o', 6.403686243786066e-2)
           , ('p', 1.2834384065798293e-2)
           , ('q', 7.138347387421628e-4)
           , ('r', 4.690632271126924e-2)
           , ('s', 5.158643719972403e-2)
           , ('t', 7.412048777412344e-2)
           , ('u', 2.4815393492062212e-2)
           , ('v', 8.374784452685366e-3)
           , ('w', 2.1703982385104428e-2)
           , ('x', 1.2415166762133473e-3)
           , ('y', 1.727545803897735e-2)
           , ('z', 3.776840141356609e-4)
           ]


-- Set up list so that all characters are included.
allChars = [ (' ', 0)
           , ('a', 0)
           , ('b', 0)
           , ('c', 0)
           , ('d', 0)
           , ('e', 0)
           , ('f', 0)
           , ('g', 0)
           , ('h', 0)
           , ('i', 0)
           , ('j', 0)
           , ('k', 0)
           , ('l', 0)
           , ('m', 0)
           , ('n', 0)
           , ('o', 0)
           , ('p', 0)
           , ('q', 0)
           , ('r', 0)
           , ('s', 0)
           , ('t', 0)
           , ('u', 0)
           , ('v', 0)
           , ('w', 0)
           , ('x', 0)
           , ('y', 0)
           , ('z', 0)
           ]


-- Score how likely it is that the string a is English text, based on letter
--   and space frequency, and proportion of characters that are lower-case
--   letters.
scoreString :: String -> Double
scoreString a = (freqDiffs (freq a) englFreq)
                * (1 - (fromIntegral $ length $ filter isLower a) / aLength)
    where freqDiffs s t = sum [abs ((snd a) - (snd b)) | (a,b) <- zip s t]
          freq a = map (\ (ch, f) -> (ch, (fromIntegral f) / aLength))
                   $ Map.toList
                   $ Map.fromListWith (+) (allChars ++ [(c, 1) | c <- a])
          aLength = fromIntegral $ length a
          isLower c
              | 'a' <= c && c <= 'z' = True
              | otherwise            = False


-- Return a list of hex strings of length 2n, where each string is composed of a
--   different ASCII character repeated n times and converted to hex.
uniformStrings :: Int -> [(Char, String)]
uniformStrings n = uniformStringsAcc 0 n []
    where uniformStringsAcc 127 _ lst = lst
          uniformStringsAcc c n lst = let k = Char.chr c in
                                      (k, (S1C1.asciiToHex $ take n [k,k..]))
                                      : uniformStringsAcc (c + 1) n lst


-- Return a triple representing the score of the best decryption, the character
--   used for decryption, and the decrypted version of the hex string a.
crackSingleCharXOR :: String -> (Double, Char, String)
crackSingleCharXOR x = List.minimumBy (\ (a,_,_) (b,_,_) -> a `compare` b)
                       (allDecryptions x)
    where allDecryptions x = [decryptWithPair x p
                                  | p <- uniformStrings ((length x) `quot` 2)]
          decryptWithPair x (c, s) =
              let dec = S1C1.hexToASCII $ S1C2.fixedXOR x s in
              (scoreString dec, c, dec)
