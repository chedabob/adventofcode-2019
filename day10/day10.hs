import qualified Data.Text as T

main :: IO ()
main = do
    s <- readFile "test.txt"
    let width = 5
    let removeLines xs = [ x | x <- xs, not (x `elem` "\n") ]
    let getindex :: Integer -> Integer -> Integer
    let getindex x y = (+ x (* y width))
    -- let getchar xs x y = xs !! (+ x (* y width))
    let lines =  removeLines s
    -- let iterations = [ [x,y] | x <- [0..10], y <- [0..10]]
    -- let pairs = [ [x, y] | x <- iterations, y <- lines !! (head x)]
    print $ (getindex 1, 0)