import sys, os
from pathlib import Path
import networkx.algorithms.cycles as cyc

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import networkx as nx
import data.dataset as ds

def main():
    filename = '/home/ankursarda/Projects/graph-analytics/networkx/data/sample_data4.adj' #sys.argv[1]
    g = ds.getDirectedData(filename)
    try:
        res = cyc.simple_cycles(g)
        print(list(res))
    except nx.exception.NetworkXNoCycle:
        print("[]")

if __name__ == '__main__':
    main()