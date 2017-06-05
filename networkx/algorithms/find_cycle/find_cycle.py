import sys, os
from pathlib import Path
import networkx.algorithms.cycles as cyc

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import networkx as nx
import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getData(filename)
    try:
        res = cyc.find_cycle(g)
        print(list(res))
    except nx.exception.NetworkXNoCycle:
        print("[]")

if __name__ == '__main__':
    main()