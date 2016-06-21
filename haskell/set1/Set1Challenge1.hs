------------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 1 Functions
-- Spring 2016
------------------------------------------


module Set1Challenge1
    ( intToHex
    , hexToInt
    , intToBase64
    , base64ToInt
    , hexToBase64
    , base64ToHex
    , asciiToHex
    , hexToASCII
    ) where


import qualified Data.Char as Char


-- Return the hex string corresponding to integer n.
intToHex :: Integer -> String
intToHex 0 = "0"
intToHex n = map (smallIntToHex . fromIntegral) (hexAppend n [])
    where hexAppend 0 lst = lst
          hexAppend n lst = hexAppend (n `quot` 16) ((n `mod` 16) : lst)
          smallIntToHex n
              | n < 0 || n > 15 = error "Invalid value for hex digit."
              | n <= 9          = Char.chr $ n + Char.ord '0'
              | otherwise       = Char.chr $ n + Char.ord 'a' - 10


-- Return the integer corresponding to the hex string x.
hexToInt :: String -> Integer
hexToInt x = foldl (\ acc n -> acc * 16 + n) 0 $
             map (toInteger . Char.digitToInt) x


-- Return the base64 string corresponding to integer n.
intToBase64 :: Integer -> String
intToBase64 n = map (smallIntToBase64 . fromIntegral) (base64Append n [])
    where base64Append 0 lst = lst
          base64Append n lst = base64Append (n `quot` 64) ((n `mod` 64) : lst)
          smallIntToBase64 n
              | n < 0 || n > 63 = error "Invalid value for base 64 digit."
              | n <= 25         = Char.chr $ n + Char.ord 'A'
              | n <= 51         = Char.chr $ n + Char.ord 'a' - 26
              | n <= 61         = Char.chr $ n + Char.ord '0' - 52
              | n == 62         = '+'
              | otherwise       = '/'                       -- must be 63


-- Return the integer corresponding to the base64 string s.
base64ToInt :: String -> Integer
base64ToInt s = sumAsBase64 $ map base64DigitToInt s
    where base64DigitToInt sc
              | 'A' <= sc && sc <= 'Z' = toInteger $
                                         Char.ord sc - Char.ord 'A'
              | 'a' <= sc && sc <= 'z' = toInteger $
                                         Char.ord sc - Char.ord 'a' + 26
              | Char.isDigit sc        = toInteger $
                                         Char.ord sc - Char.ord '0' + 52
              | sc == '+'              = 62
              | sc == '/'              = 63
              | otherwise              = error "Invalid base64 digit."
          sumAsBase64 lst = foldl (\ acc n -> acc * 64 + n) 0 lst


-- Return the base64 string corresponding to hex string x.
hexToBase64 :: String -> String
hexToBase64 = intToBase64 . hexToInt


-- Return the hex string corresponding to base64 string s.
base64ToHex :: String -> String
base64ToHex = intToHex . base64ToInt


-- Return the hex string corresponding to ASCII string a.
asciiToHex :: String -> String
asciiToHex a = concat $ map (\ c -> (if (Char.ord c) <= 15 then "0" else "")
                                     ++ (intToHex $ toInteger $ Char.ord c)) a


-- Return the ASCII string corresponding to hex string x.
hexToASCII :: String -> String
hexToASCII x = map hexByteToASCII (chunk 2 x)
    where chunk n [] = []
          chunk n lst
              | n > 0     = (take n lst) : (chunk n (drop n lst))
              | otherwise = error "Invalid first argument to (chunk)."
          hexByteToASCII b = Char.chr $ fromIntegral $ hexToInt b
