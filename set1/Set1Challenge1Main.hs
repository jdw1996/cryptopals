----------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 1 Program
-- Spring 2016
----------------------------------------


import Set1Challenge1


main = do
  let x = "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f6973\
          \6f6e6f7573206d757368726f6f6d"
      s = "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
  putStrLn "Given the hex string:"
  putStrLn ("\"" ++ x ++ "\"")
  putStrLn "the base-64 encoding should be:"
  putStrLn ("\"" ++ s ++ "\"")
  if (hexToBase64 x == s)
  then putStrLn "This is computed correctly by the program."
  else putStrLn "Unfortunately, the program does not compute this correctly."
  putStrLn ""
  putStrLn "This string translates in ASCII to:"
  putStrLn ("\"" ++ (hexToASCII x) ++ "\"")
