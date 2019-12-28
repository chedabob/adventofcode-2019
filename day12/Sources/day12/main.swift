class Moon : CustomDebugStringConvertible {
    var position : Position
    var velocity : Velocity

    func applyGravity () {
        self.position.x += self.velocity.x
        self.position.y += self.velocity.y
        self.position.z += self.velocity.z
    }

    func adjustVelocity(otherMoon : inout Moon) {
        doX(&otherMoon)
        doY(&otherMoon)
        doZ(&otherMoon)
    }

    func doX (_ otherMoon : inout Moon) {
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

    func doY (_ otherMoon : inout Moon) {
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

    func doZ (_ otherMoon : inout Moon) {
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
    }

    var debugDescription: String {
        return "pos=\(position), vel=\(velocity)"
    }
}

class Position : CustomDebugStringConvertible {
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
}

class Velocity : CustomDebugStringConvertible  {
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


for s in 0..<1000 {
    // print(s)
    // let joined = system.map({"\($0)" }).joined(separator:"\n")
    // print(joined)
    step (input: &system)
}

let sum = system.map({ $0.energy() }).reduce(0, +)

print(sum)
