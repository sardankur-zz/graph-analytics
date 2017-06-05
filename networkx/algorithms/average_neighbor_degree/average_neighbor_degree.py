import sys, os
from pathlib import Path
import networkx.algorithms.assortativity.neighbor_degree as av

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getDirectedData(filename)
    res = av.average_neighbor_degree(g)
    res = {k: '%.3f' % v for k, v in res.items()}
    print(res)


if __name__ == '__main__':
    main()
