import networkx.algorithms.approximation.independent_set as mis
import data.dataset as ds

import sys

def main():
    # filename = sys.argv(1)
    g = ds.getDirectedData('/home/ankursarda/Projects/graph-analytics/networkx/data/sample_data4.adj')
    res = mis.maximum_independent_set(g)
    print(res)

if __name__ == '__main__':
    main()