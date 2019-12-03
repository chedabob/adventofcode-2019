use std::cmp;
use std::fs;
use array2d::Array2D;

fn main() {
    part1();
    part2();
}

fn part1() {
    test("R8,U5,L5,D3".to_string(), "U7,R6,D4,L4".to_string());
    test("R75,D30,R83,U83,L12,D49,R71,U7,L72".to_string(), "U62,R66,U55,R34,D71,R55,D58,R83".to_string());
    test("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".to_string(), "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".to_string());

    run();
}

fn part2() {
    test2("R8,U5,L5,D3".to_string(), "U7,R6,D4,L4".to_string());
    test2("R75,D30,R83,U83,L12,D49,R71,U7,L72".to_string(), "U62,R66,U55,R34,D71,R55,D58,R83".to_string());
    test2("R98,U47,R26,D63,R33,U87,L62,D20,R33,U53,R51".to_string(), "U98,R91,D20,R16,D67,R40,U7,R15,U6,R7".to_string());

    run2();
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

fn run2 () {
    let contents = fs::read_to_string("./input.txt");
    match contents {
        Ok(i) => {
            let lines:Vec<&str> = i.split("\n").collect();
            test2(lines[0].to_string(), lines[1].to_string());
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

    walk_grid(&line1, &mut grid);
    // println!("Walked first path");
    let manhatdist =  run_intersect(&line2, &grid);
    println!("Distance: {}", manhatdist);
}

fn test2(in1 : String, in2: String) {
    let line1:Vec<&str> = in1.split(",").collect();
    let line2:Vec<&str> = in2.split(",").collect();

    let mut grid = Array2D::filled_with(0, 25000, 25000);
    // println!("{:?}", line1);
    // println!("{:?}", line2);

    walk_grid2(&line1, &mut grid, 1);
    walk_grid2(&line2, &mut grid, 2);

    // println!("Walked first path");
    let line1_intersections = check_distances(&line1, &mut grid);
    // println!("Inter1{:?}", line1_intersections);
    let line2_intersections = check_distances(&line2, &mut grid);
    // println!("Inter2{:?}", line2_intersections);

    let mut curr_best_distance = 300000i32;
    for (index, instruction) in line1_intersections.iter().enumerate() {
        let line1_inter = line1_intersections[index].1;
        let line2_inter = line2_intersections[index].1;

        let sum = line1_inter + line2_inter;
        curr_best_distance = cmp::min(sum, curr_best_distance);

    }

    println!("Best sum {}", curr_best_distance);
}

fn walk_grid(instructions: &Vec<&str>, grid : &mut Array2D<i32>) {
    let offset = 12500;

    let mut curr_x = offset;
    let mut curr_y = offset;

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
                'R' => { curr_x += 1 }
                'L' => { curr_x -= 1 }
                'U' => { curr_y += 1 } 
                'D' => { curr_y -= 1 }
                _ => { println!("Not found direction");}
            }
            
            grid[(curr_x, curr_y)] += 1;
            stride = stride - 1;
        }
    }
}

fn walk_grid2(instructions: &Vec<&str>, grid : &mut Array2D<i32>, identifier : i32) {
    let offset = 12500;

    let mut curr_x = offset;
    let mut curr_y = offset;

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
                'R' => { curr_x += 1 }
                'L' => { curr_x -= 1 }
                'U' => { curr_y += 1 } 
                'D' => { curr_y -= 1 }
                _ => { println!("Not found direction");}
            }

            grid[(curr_x, curr_y)] += identifier;
            stride = stride - 1;
        }
    }
}

fn run_intersect(instructions: &Vec<&str>, grid : &Array2D<i32>) -> i32 {
    let offset = 12500;
    let mut curr_x = offset;
    let mut curr_y = offset;

    let mut curr_min_manhatt = 30000i32;

    for (index, instruction) in instructions.iter().enumerate() {
        let direction = instruction.chars().next().unwrap();
        let mut stride = 0;
        stride = instruction[1..].parse::<i32>().unwrap();
        
        // println!("Direction {:?}", direction);
        // println!("Stride {:?}", stride);
        let min : i32 = 0;
        while stride > min {
            match direction {
                'R' => { curr_x += 1 }
                'L' => { curr_x -= 1 }
                'U' => { curr_y += 1 } 
                'D' => { curr_y -= 1 }
                _ => { println!("Not found direction");}
            }
            if grid[(curr_x, curr_y)] > 0 {
                let new_x  = curr_x as i32 - offset as i32;
                let new_y  = curr_y as i32 - offset as i32;

                let manhat = new_x.abs() + new_y.abs();
                // println!("Found overlap at X: {} Y: {} Distance: {}", newX, newY, manhat);

                curr_min_manhatt = cmp::min(manhat, curr_min_manhatt);
            }

            stride = stride - 1;
        }
    }
    
    return curr_min_manhatt
}

fn check_distances(instructions: &Vec<&str>, grid : &Array2D<i32>) -> Vec<(i32, i32)> {
    let offset = 12500;
    let mut curr_x = offset;
    let mut curr_y = offset;

    let mut intersections: Vec<(i32, i32)> = vec![];

    let mut wire_length = 1i32;

    for (index, instruction) in instructions.iter().enumerate() {
        let direction = instruction.chars().next().unwrap();
        let mut stride = 0;
        stride = instruction[1..].parse::<i32>().unwrap();
        
        // println!("Direction {:?}", direction);
        // println!("Stride {:?}", stride);
        let mut to_walk = stride;
        while stride > 0i32 {
            match direction {
                'R' => { curr_x += 1 }
                'L' => { curr_x -= 1 }
                'U' => { curr_y += 1 } 
                'D' => { curr_y -= 1 }
                _ => { println!("Not found direction");}
            }
            if grid[(curr_x, curr_y)] == 3 {
                let new_x  = curr_x as i32 - offset as i32;
                let new_y  = curr_y as i32 - offset as i32;

                let manhat = new_x.abs() + new_y.abs();
                intersections.push((manhat, wire_length));
            }

            stride = stride - 1;
            wire_length += 1;
        }
    }

    
    intersections.sort_by(|a,b| b.0.cmp(&a.0));
    return intersections
}


