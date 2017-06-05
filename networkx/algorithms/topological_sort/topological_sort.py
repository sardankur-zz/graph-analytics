import sys, os
from pathlib import Path
import networkx.algorithms.dag as dag

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getDirectedData(filename)
    res = dag.topological_sort(g)
    print(res)


if __name__ == '__main__':
    main()
