import networkx as nx

def getData(filename):
    g = None
    with open(filename) as f:
        content = f.readlines()
        g = nx.Graph()
        for line in content:
            c = line.split(' ')
            n = c[0]
            for n1 in c[1:]:
                g.add_edge(int(n),int(n1))
    return g

def getDirectedData(filename):
    g = None
    with open(filename) as f:
        content = f.readlines()
        g = nx.DiGraph()
        for line in content:
            c = line.split(' ')
            n = c[0]
            for n1 in c[1:]:
                g.add_edge(int(n),int(n1))
    return g

def getDataWithCost(filename):
    g = None
    with open(filename) as f:
        content = f.readlines()
        g = nx.Graph()
        for line in content:
            line = line.strip()
            c = line.split(' ')
            n = c[0]
            for n1, cost in zip(c[1::2], c[2::2]):
                g.add_edge(int(n), int(n1), weight = float(cost))
    return g

def geDirectedDataWithCost(filename):
    g = None
    with open(filename) as f:
        content = f.readlines()
        g = nx.DiGraph()
        for line in content:
            line = line.strip()
            c = line.split(' ')
            n = c[0]
            for n1, cost in zip(c[1::2], c[2::2]):
                g.add_edge(int(n), int(n1), weight = float(cost))
    return g
