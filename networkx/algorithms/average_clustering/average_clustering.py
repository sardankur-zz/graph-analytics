import sys, os
from pathlib import Path
import networkx.algorithms.approximation.clustering_coefficient as cc

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getDataWithCost(filename)
    res = cc.average_clustering(g)
    print(res)

if __name__ == '__main__':
    main()
