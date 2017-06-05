import networkx as nx
import networkx.algorithms.approximation.clique as cq
import data.dataset as ds

def main():
    # g = ds.getDataWithCost('/home/ankursarda/Projects/graph-analytics/networkx/data/sample_data1.adj')
    # res = cyc.cycle_basis(g)
    # res = cyc.find_cycle(g)
    g = ds.getData('/home/ankursarda/Projects/graph-analytics/networkx/data/sample_data1.adj')
    # iset2, clique2 = cq.clique_removal(g)
    iset3 = cq.max_clique(g)
    # print(max(clique2), end='\n')
    print(iset3)

if __name__ == '__main__':
    main()