def make_operation (input):
    opcode = ""
    param1Mode = False
    param2Mode = False
    param3Mode = False

    parsed = list(str(input))
    parsed.reverse()

    idx = 0
    while idx < len(parsed):
        comp = parsed[idx]
        if idx == 0:
            opcode = comp
        elif idx == 1:
            opcode = comp + opcode
        elif idx == 2:
            if comp == "1":
                param1Mode = True
        elif idx == 3:
            if comp == "1":
                param2Mode = True
        elif idx == 4:
            if comp == "1":
                param3Mode = True
        idx +=1


    return [int(opcode), param1Mode, param2Mode, param3Mode]

def get_value (input, index, mode):
    if mode == False:
        return instructions[index]
    else:
        return index

def get_dest (input, index, mode):
    if mode == False:
        return index
    else:
        return instructions[index]

instructions = []

with open('input.txt', 'r') as file:
    data = file.read()
    instructions = list(map(int, data.split(",")))

# 1 - Add next two integers together, store in 3rd
# 2 - Multiply two integers together, store in 3rd
# 3 - Store input at address
# 4 - Output value at address
# 99 - End

input = 1
output = 0

idx = 0

print("Have instructions", len(instructions))

# instructions[1] = 12
# instructions[2] = 2



while idx < len(instructions):
    raw = instructions[idx]
    print("Raw {}".format(raw))

    operation = make_operation(raw)

    val = operation[0]
    param1Mode = operation[1]
    param2Mode = operation[2]
    param3Mode = operation[3]

    if val == 1:
        sl = instructions[idx + 1: idx + 4]
        print("Add slice {}".format(sl))
        # print("Slice",  sl)
        # print(instructions[idx:idx+10])
        p1 = get_value(instructions, sl[0], param1Mode)
        p2 = get_value(instructions, sl[1], param2Mode)
        newval = p1 + p2
        # dest = sl[2]
        dest = get_dest(instructions, sl[2], param3Mode)
        instructions[dest] = newval
        idx += 4
    elif val == 2:
        sl = instructions[idx + 1: idx + 4]
        print("Mul slice {}".format(sl))
        p1 = get_value(instructions, sl[0], param1Mode)
        p2 = get_value(instructions, sl[1], param2Mode)
        newval = p1 * p2
        # dest = sl[2]
        dest = get_dest(instructions, sl[2], param3Mode)
        instructions[dest] = newval
        idx += 4
    elif val == 3:
        dest = get_dest(instructions, instructions[idx+1], param1Mode)
        instructions[dest] = input
        print("Input set {} to value {}".format(dest, input))
        idx += 2
    elif val == 4:
        dest = get_dest(instructions, instructions[idx+1], param1Mode)
        output = instructions[dest]
        print("Output set {} to value {}".format(dest, output))

        idx += 2
    else:
        print("Breaking at val {}".format(val))
        break

print ("Diagnostic code ", output)


    