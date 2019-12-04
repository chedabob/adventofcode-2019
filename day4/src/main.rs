fn main() {
    part1();
}

fn part1() {
    let range_lower = 272091i32;
    let range_higher = 815432i32;
    let mut num_passwords = 0i32;

    for val in range_lower..=range_higher {
        if is_six_digit(val) && is_descending(val) && has_consecutive(val) {
            //println!("{}", val);
            num_passwords += 1;
        }
    }

    println!("Num Passwords: {}", num_passwords);
}


fn is_six_digit(input : i32) -> bool {
    input > 99999 && input < 1000000
}

fn is_descending (input : i32) -> bool {
    let s : String = input.to_string();
    let mut chars : Vec<char> = s.chars().collect();
    chars.sort();
    let reassembled : String = chars.into_iter().collect();
    s == reassembled
}

fn has_consecutive (input : i32) -> bool {
    let s : String = input.to_string();
    let chars : Vec<char> = s.chars().collect();
    for idx in 1..chars.len() {
        if chars[idx] == chars[idx -1] {
            return true;
        }
    }
    return false;
}