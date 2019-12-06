class SpaceObject:
    def __init__(self, id):
        self.id = id
        self.orbiting = []
    def __str__(self):
        return "{} with orbiting: {}".format(self.id, "".join(map(str, self.orbiting)))
    
    def walk(self, depths, currdepth):
        depths[self.id] = currdepth
        nextdepth = currdepth + 1
        for sub in self.orbiting:
            sub.walk(depths, nextdepth)
        return depths


def max_depth(tree):
    count = 0
    common = tree["COM"]
    depths =  common.walk({}, 0)
    return sum(depths.values())

def load_map(filename):
    with open(filename, 'r') as file:
        data = file.read()
        return data.split("\n")


def run(input):
    spaceobjs = {}
    # # Insert the common obj
    # common = SpaceObject("COM)COM")
    # spaceobjs[common.id] = common

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

    return max_depth(spaceobjs)

def test():
    mp = load_map('test.txt')
    depth = run(mp)
    assert depth == 42
    print("Test depth", depth)

def day1():
    mp = load_map('input.txt')
    depth = run(mp)
    print("Day1 depth", depth)

test()
day1()
