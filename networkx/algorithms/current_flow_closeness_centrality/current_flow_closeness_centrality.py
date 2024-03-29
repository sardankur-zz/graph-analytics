import sys, os
from pathlib import Path

import networkx.algorithms.centrality.current_flow_closeness as cfc

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getDataWithCost(filename)
    res = cfc.current_flow_closeness_centrality(g)
    res = {k: '%.3f' % v for k, v in res.items()}
    print(res)

if __name__ == '__main__':
    main()