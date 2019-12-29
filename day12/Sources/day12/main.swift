
// Source: https://github.com/raywenderlich/swift-algorithm-club/tree/master/GCD
class Math {
    static func greatestCommonDivisorIterativeEuklid(_ m: Int, _ n: Int) -> Int {
        var a: Int = 0
        var b: Int = max(m, n)
        var r: Int = min(m, n)
        
        while r != 0 {
            a = b
            b = r
            r = a % b
        }
        return b
    }

    static func leastCommonMultiple(_ m: Int, _ n: Int) -> Int {
        guard (m & n) != 0 else { fatalError() }
        return m / greatestCommonDivisorIterativeEuklid(m, n) * n
    }
}

class Moon : CustomDebugStringConvertible, Hashable {
    var position : Position
    var velocity : Velocity

    var initialPosition : Position

    func applyGravity () {
        applyXGravity()
        applyYGravity()
        applyZGravity()
    }

    func applyXGravity () {
        self.position.x += self.velocity.x
    }

    func applyYGravity () {
        self.position.y += self.velocity.y
    }

    func applyZGravity () {
        self.position.z += self.velocity.z
    }

    func adjustVelocity(otherMoon : inout Moon) {
        adjustXVelocity(&otherMoon)
        adjustYVelocity(&otherMoon)
        adjustZVelocity(&otherMoon)
    }

    func adjustXVelocity (_ otherMoon : inout Moon) {
        guard otherMoon.position.x != self.position.x else { return }
        if self.position.x < otherMoon.position.x {
            self.velocity.x += 1
            otherMoon.velocity.x -= 1
        }
        else {
            self.velocity.x -= 1
            otherMoon.velocity.x += 1
        }
    }

    func adjustYVelocity (_ otherMoon : inout Moon) {
        guard otherMoon.position.y != self.position.y else { return }
        if self.position.y < otherMoon.position.y {
            self.velocity.y += 1
            otherMoon.velocity.y -= 1
        }
        else {
            self.velocity.y -= 1
            otherMoon.velocity.y += 1
        }
    }

    func adjustZVelocity (_ otherMoon : inout Moon) {
        guard otherMoon.position.z != self.position.z else { return }
        if self.position.z < otherMoon.position.z {
            self.velocity.z += 1
            otherMoon.velocity.z -= 1
        }
        else {
            self.velocity.z -= 1
            otherMoon.velocity.z += 1
        }
    }

    func potentialEnergy () -> Int {
        return abs(self.position.x) + abs(self.position.y) + abs(self.position.z)
    }

    func kineticEnergy () -> Int {
        return abs(self.velocity.x) + abs(self.velocity.y) + abs(self.velocity.z)
    }

    func energy () -> Int {
        return potentialEnergy() * kineticEnergy()
    }

    init (position: Position, velocity: Velocity) {
        self.velocity = velocity
        self.position = position
        self.initialPosition = Position(x: position.x, y: position.y, z: position.z)
    }

    var debugDescription: String {
        return "pos=\(position), vel=\(velocity)"
    }

    var hashValue: Int {
        return position.hashValue ^ velocity.hashValue
    }

    var atInitialX : Bool {
        return self.position.x == self.initialPosition.x && self.velocity.x == 0
    }

    var atInitialY : Bool {
        return self.position.y == self.initialPosition.y && self.velocity.y == 0
    }

    var atInitialZ : Bool {
        return self.position.z == self.initialPosition.z && self.velocity.z == 0
    }

    static func == (lhs: Moon, rhs: Moon) -> Bool {
        return lhs.position == rhs.position && lhs.velocity == rhs.velocity
    }
}

class Position : CustomDebugStringConvertible, Hashable {
    var x : Int
    var y : Int
    var z : Int

    init (x : Int, y : Int, z: Int) {
        self.x = x
        self.y = y
        self.z = z
    }

    var debugDescription: String {
        return "<x= \(x), y=\(y), z= \(z)>"
    }

    var hashValue: Int {
        return x.hashValue ^ y.hashValue ^ z.hashValue
    }

    static func == (lhs: Position, rhs: Position) -> Bool {
        return lhs.x == rhs.x && lhs.y == rhs.y && lhs.z == rhs.z
    }
}

class Velocity : CustomDebugStringConvertible, Hashable  {
    var x : Int
    var y : Int
    var z : Int
    init (x : Int, y : Int, z: Int) {
        self.x = x
        self.y = y
        self.z = z
    }

    var debugDescription: String {
        return "<x= \(x), y=\(y), z= \(z)>"
    }

    var hashValue: Int {
        return x.hashValue ^ y.hashValue ^ z.hashValue
    }

    static func == (lhs: Velocity, rhs: Velocity) -> Bool {
        return lhs.x == rhs.x && lhs.y == rhs.y && lhs.z == rhs.z
    }
}


var system = [
    Moon(position: Position(x: 17, y: -9, z: 4), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: 2, y: 2, z: -13), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: -1, y: 5, z: -1), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: 4, y: 7, z: -7), velocity: Velocity(x: 0, y: 0, z: 0))
]



// var system = [
//     Moon(position: Position(x: -1, y: 0, z: 2), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 2, y: -10, z: -7), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 4, y: -8, z: 8), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 3, y: 5, z: -1), velocity: Velocity(x: 0, y: 0, z: 0))
// ]



func step (input: inout [Moon]) {
    let pairs = [
        (0,1),
        (0,2),
        (0,3),
        (1,2),
        (1,3),
        (2,3)
    ]

    for p in pairs {
        var planetA = input[p.0]
        var planetB = input[p.1]

        planetA.adjustVelocity(otherMoon: &planetB)
    }

    for index in input.indices { 
        input[index].applyGravity() 
    }
}

for _ in 0..<1000 {
    step (input: &system)
}


let sum = system.map({ $0.energy() }).reduce(0, +)

print("Day 12 Part 1 \(sum)")


var system2 = [
    Moon(position: Position(x: 17, y: -9, z: 4), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: 2, y: 2, z: -13), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: -1, y: 5, z: -1), velocity: Velocity(x: 0, y: 0, z: 0)),
    Moon(position: Position(x: 4, y: 7, z: -7), velocity: Velocity(x: 0, y: 0, z: 0))
]



// var system2 = [
//     Moon(position: Position(x: -8, y: -10, z: 0), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 5, y: 5, z: 10), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 2, y: -7, z: 3), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 9, y: -8, z: -3), velocity: Velocity(x: 0, y: 0, z: 0))
// ]


// var system2 = [
//     Moon(position: Position(x: -1, y: 0, z: 2), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 2, y: -10, z: -7), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 4, y: -8, z: 8), velocity: Velocity(x: 0, y: 0, z: 0)),
//     Moon(position: Position(x: 3, y: 5, z: -1), velocity: Velocity(x: 0, y: 0, z: 0))
// ]

var foundX : Int? = nil
var foundY : Int? = nil
var foundZ : Int? = nil
for s in 1..<100000000 {
    step (input: &system2)

    if (system2.allSatisfy({$0.atInitialX}) && foundX == nil) {
        foundX = s
        print("Found X at \(s)")
    }

    if (system2.allSatisfy({$0.atInitialY}) && foundY == nil) {
        foundY = s
        print("Found Y at \(s)")
    }

    if (system2.allSatisfy({$0.atInitialZ}) && foundZ == nil) {
        foundZ = s
        print("Found Z at \(s)")
    }

    if foundX != nil && foundY != nil && foundZ != nil {
        print("Found all")
        break
    }
}

let leastCommonMultiple = [foundX!, foundY!, foundZ!].reduce(foundX!, {Math.leastCommonMultiple($0, $1)})
print("Day 12 Part 2 \(leastCommonMultiple)")

119524490127900
468656273604645
4302967224744805
