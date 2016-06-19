----------------------------------------
-- Joseph Winters
-- Cryptopals Set 1 Challenge 3 Program
-- Spring 2016
----------------------------------------


import Set1Challenge3


main = do
  let x = "1b37373331363f78151b7f2b783431333d78397828372d363c78373e783a393b3736"
      (score, ch, dec) = crackSingleCharXOR x
  putStrLn "Given the hex string:"
  putStrLn ("\"" ++ x ++ "\"")
  putStrLn "the decryption by single character XOR is:"
  putStrLn ("\"" ++ dec ++ "\"")
  putStrLn ""
  putStr "This decryption received a score of "
  putStr . show $ score
  putStr " (lower is better) and\nwas carried out with the character "
  putStr . show $ ch
  putStrLn "."
