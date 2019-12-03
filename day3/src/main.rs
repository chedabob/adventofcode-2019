use std::cmp;
use std::fs;
use array2d::Array2D;

fn main() {
    test("R8,U5,L5,D3".to_string(), "U7,R6,D4,L4".to_string());
    test("R75,D30,R83,U83,L12,D49,R71,U7,L72".to_string(), "U62,R66,U55,R34,D71,R55,D58,R83".to_string());
    test("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".to_string(), "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".to_string());

    run();
}

fn run () {
    let contents = fs::read_to_string("./input.txt");
    match contents {
        Ok(i) => {
            let lines:Vec<&str> = i.split("\n").collect();
            test(lines[0].to_string(), lines[1].to_string());
        }
        _ => {}
    }
}

fn test(in1 : String, in2: String) {
    let line1:Vec<&str> = in1.split(",").collect();
    let line2:Vec<&str> = in2.split(",").collect();

    let mut grid = Array2D::filled_with(0, 25000, 25000);
    // println!("{:?}", line1);
    // println!("{:?}", line2);

    grid = walk_grid(line1, grid);
    // println!("Walked first path");
    let manhatdist =  run_intersect(line2, grid);
    println!("Distance: {}", manhatdist);
}

fn walk_grid(instructions: Vec<&str>, grid : Array2D<i32>) -> Array2D<i32> {
    let mut ourGrid = grid;
    let offset = 12500;

    let mut currX = offset;
    let mut currY = offset;

    for (index, instruction) in instructions.iter().enumerate() {
        // println!("CurrX {:?} Curr Y {:?} ", currX, currY);

        let direction = instruction.chars().next().unwrap();
        let mut stride = 0;
        stride = instruction[1..].parse::<i32>().unwrap();
        
        // println!("Direction {:?}", direction);
        // println!("Stride {:?}", stride);
        let mut to_walk = stride;
        let min : i32 = 0;
        while stride > min {
            match direction {
                'R' => { currX += 1 }
                'L' => { currX -= 1 }
                'U' => { currY += 1 } 
                'D' => { currY -= 1 }
                _ => { println!("Not found direction");}
            }
            ourGrid[(currX, currY)] += 1;
            stride = stride - 1;
        }
    }
    
    return ourGrid
}

fn run_intersect(instructions: Vec<&str>, grid : Array2D<i32>) -> i32 {
    let mut ourGrid = grid;
    let offset = 12500;
    let mut currX = offset;
    let mut currY = offset;

    let mut currMinManhatt = 30000i32;

    for (index, instruction) in instructions.iter().enumerate() {
        let direction = instruction.chars().next().unwrap();
        let mut stride = 0;
        stride = instruction[1..].parse::<i32>().unwrap();
        
        // println!("Direction {:?}", direction);
        // println!("Stride {:?}", stride);
        let mut to_walk = stride;
        let min : i32 = 0;
        while stride > min {
            match direction {
                'R' => { currX += 1 }
                'L' => { currX -= 1 }
                'U' => { currY += 1 } 
                'D' => { currY -= 1 }
                _ => { println!("Not found direction");}
            }
            if ourGrid[(currX, currY)] > 0 {
                let newX  = (currX as i32 - offset as i32);
                let newY  = (currY as i32 - offset as i32);

                let manhat = (newX.abs() + newY.abs());
                // println!("Found overlap at X: {} Y: {} Distance: {}", newX, newY, manhat);

                currMinManhatt = cmp::min(manhat, currMinManhatt)
            }

            // ourGrid[(currX, currY)] += 1;
            stride = stride - 1;
        }
    }
    
    return currMinManhatt
}

