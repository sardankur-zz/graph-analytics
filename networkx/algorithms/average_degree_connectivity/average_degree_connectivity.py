import sys, os
from pathlib import Path

import networkx.algorithms.assortativity.connectivity as adc


p = Path(os.getcwd())
sys.path.append(str(p) + '/networkx')

import data.dataset as ds

def main():
    filename = sys.argv[1]
    g = ds.getDirectedData(filename)
    res = adc.average_degree_connectivity(g)
    res = {k: '%.3f' % v for k, v in res.items()}
    print(res)


if __name__ == '__main__':
    main()
