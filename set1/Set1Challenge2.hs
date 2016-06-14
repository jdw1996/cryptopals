------------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 2 Functions
-- Spring 2016
------------------------------------------


module Set1Challenge2 (fixedXOR) where


import qualified Set1Challenge1 as S1C1
import qualified Data.Bits as Bits


-- Return the result of xor-ing the two hex strings x and y.
fixedXOR :: String -> String -> String
fixedXOR x y = S1C1.intToHex $ (S1C1.hexToInt x) `Bits.xor` (S1C1.hexToInt y)
