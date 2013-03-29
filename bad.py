import threading

num = 1000000
x = 0


def f():
    global x
    x = x + 1
    print x


if __name__ == '__main__':
    threads = [threading.Thread(target=f) for _ in range(num)]
    for t in threads:
        t.start()

    for t in threads:
        t.join()

    print x
    print "lost updates: ", num - x
