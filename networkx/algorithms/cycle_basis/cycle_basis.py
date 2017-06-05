import sys, os
from pathlib import Path
import networkx.algorithms.cycles as cyc

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getData(filename)
    res = cyc.cycle_basis(g)
    print(list(res))

if __name__ == '__main__':
    main()