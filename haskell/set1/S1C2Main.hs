{-------------------------------------
 Joseph Winters
 Cryptopals Set 1 Challenge 2 Program
 Spring 2016
-------------------------------------}


import Set1Challenge1
import Set1Challenge2


main = do
  let x = "1c0111001f010100061a024b53535009181c"
      y = "686974207468652062756c6c277320657965"
      xXORy = "746865206b696420646f6e277420706c6179"
  putStrLn "The XOR of the hex string:"
  putStrLn ("\"" ++ x ++ "\"")
  putStrLn "and the hex string:"
  putStrLn ("\"" ++ y ++ "\"")
  putStrLn "should be:"
  putStrLn ("\"" ++ xXORy ++ "\"")
  if (fixedXOR x y == xXORy)
  then putStrLn "This is computed correctly by the program."
  else putStrLn "Unfortunately, the program does not compute this correctly."
  putStrLn ""
  putStrLn "The second string translates in ASCII to:"
  putStrLn ("\"" ++ (hexToASCII y) ++ "\"")
  putStrLn "and the third string translates in ASCII to:"
  putStrLn ("\"" ++ (hexToASCII xXORy) ++ "\"")
