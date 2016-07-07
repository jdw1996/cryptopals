------------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 3 Functions
-- Spring 2016
------------------------------------------


module Set1Challenge3 (crackSingleCharXOR) where


import qualified Data.Char as Char
import qualified Data.List as List

import qualified Set1Challenge1 as S1C1
import qualified Set1Challenge2 as S1C2


-- Score how likely it is that ASCII string a is English text.
scoreString :: String -> Double
scoreString a = (sum $ map scoreChar a) / aLength
    where scoreChar c = (if (isPrintableASCII c) then 1 else 0)
                        + (if (isASCIILetterOrSpace c) then 1 else 0)
          isPrintableASCII c
              | ' ' <= c && c <= '~' = True
              | otherwise            = False
          isASCIILetterOrSpace c
              | ('a' <= c && c <= 'z')
                || ('A' <= c && c <= 'Z')
                || (c == ' ')             = True
              | otherwise                 = False
          aLength = fromIntegral $ length a


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
crackSingleCharXOR x = List.maximumBy (\ (a,_,_) (b,_,_) -> a `compare` b)
                       (allDecryptions x)
    where allDecryptions x = [decryptWithPair x p
                                  | p <- uniformStrings ((length x) `quot` 2)]
          decryptWithPair x (c, s) =
              let dec = S1C1.hexToASCII $ S1C2.fixedXOR x s in
              (scoreString dec, c, dec)
