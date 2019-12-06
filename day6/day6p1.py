class SpaceObject:
    def __init__(self, id):
        self.id = id
        self.orbiting = []
        self.parent = None
    def __str__(self):
        return "{} with orbiting: {}".format(self.id, "".join(map(str, self.orbiting)))
    
    def walk(self, depths, currdepth):
        depths[self.id] = currdepth
        nextdepth = currdepth + 1
        for sub in self.orbiting:
            sub.walk(depths, nextdepth)
        return depths


    def find(self, tofind, numtransfers, tree, visited):
        visited.append(self)
        if self.id == tofind:
            return (True, numtransfers)
        else:
            candidates = self.orbiting.copy()
            if self.parent != None:
                candidates.append(self.parent)

            viable = [x for x in candidates if x not in visited]
            found = False
            newnumtransfers = numtransfers
            for v in viable:
                vidistance = v.find(tofind, numtransfers + 1, tree, visited)  
                if vidistance[0] == True:
                    newnumtransfers = vidistance[1]
                    found = True
                    break
            return (found, newnumtransfers)

def calc_transfers(tree):
    start = tree["YOU"]
    dst = "SAN"
    return start.find(dst, 0, tree, [])[1] - 2

def max_depth(tree):
    count = 0
    common = tree["COM"]
    depths =  common.walk({}, 0)
    return sum(depths.values())

def load_map(filename):
    with open(filename, 'r') as file:
        data = file.read()
        return data.split("\n")


def build(input):
    spaceobjs = {}

    for obj in input:
        comps = obj.split(")")
        id = comps[1]
        toorbit = comps[0]
        existing = spaceobjs.get(id)
        if existing == None:
            existing = SpaceObject(id)
            spaceobjs[existing.id] = existing

        orbiting = spaceobjs.get(toorbit)
        if orbiting == None:
            orbiting = SpaceObject(toorbit)
            spaceobjs[orbiting.id] = orbiting

        orbiting.orbiting.append(existing)
        existing.parent = orbiting

    return spaceobjs


def testpart1():
    mp = load_map('test.txt')
    objs = build(mp)
    depth = max_depth(objs)
    assert depth == 42
    print("Test depth", depth)


def part1():
    mp = load_map('input.txt')
    objs = build(mp)
    depth = max_depth(objs)
    print("Part1 depth", depth)

def testpart2():
    mp = load_map('test2.txt')
    objs = build(mp)
    transfers = calc_transfers(objs)
    print("Test transfers", transfers)
    assert transfers == 4


def part2():
    mp = load_map('input.txt')
    objs = build(mp)
    transfers = calc_transfers(objs)
    print("Part2 transfers", transfers)




testpart1()
part1()

testpart2()
part2()