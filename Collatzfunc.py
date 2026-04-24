def test_collatz_conjecture(limit=1_000_000):
    #  1 terminates at 1 = true
    terminates = {1: True}

    for n in range(2, limit):
        sequence = []   # not yet resolved
        while n not in terminates:
            sequence.append(n)
            if n % 2 == 0:
                n //= 2
            else:
                n = 3 * n + 1

        # n is in 'terminates'= reached 1
        for num in sequence:
            terminates[num] = True

    # After loop, all numbers < limit have been processed
    print(f"All numbers from 1 to {limit-1} terminate at 1.")
    return True

if __name__ == "__main__":
    test_collatz_conjecture(1_000_000)