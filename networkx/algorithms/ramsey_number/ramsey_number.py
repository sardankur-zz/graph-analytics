import networkx as nx
import networkx.algorithms.approximation.ramsey as rm
import data.dataset as ds

def main():
    g = ds.getData('/home/ankursarda/Projects/graph-analytics/networkx/data/sample_data2.adj')
    res = rm.ramsey_R2(g)
    print(res)

if __name__ == '__main__':
    main()