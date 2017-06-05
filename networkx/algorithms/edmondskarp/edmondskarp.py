import sys, os
from pathlib import Path

import networkx.algorithms.flow.edmondskarp as ek

p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    source = int(sys.argv[2])
    dest = int(sys.argv[3])
    g = ds.getDataWithCost(filename)
    res = ek.edmonds_karp(g, source, dest, capacity='weight', value_only=True)
    print(res.graph['flow_value'])

if __name__ == '__main__':
    main()