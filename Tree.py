class Node:
  def __init__(self, value):
    self.value = value # data
    self.children = [] # references to other nodes
    self.parent = ''
    self.not_printed = True
    
  def __repr__(self):
    return self.value
    
  def add_child(self, child_node):
    # creates parent-child relationship
    print("Adding parent: " + self.value + " child: " + child_node.value)
    self.children.append(child_node)
    child_node.parent = self
    return child_node
    
  def traverse(self):
    # moves through each node referenced from self downwards
    nodes_to_visit = [self]
    while len(nodes_to_visit) > 0:
      current_node = nodes_to_visit.pop()
      print(current_node.value)
      nodes_to_visit += current_node.children
      
if __name__ == "__main__":
    arr = ["a"," b", " c", "  d", " e"]

    root = Node(arr[0])
    for string in arr[1:]:
        if string[0] == ' ' and string[1] != ' ': # one space
            current = root.add_child(Node(string[1:]))
        elif string[0:2] == '  ': # two space
            current = current.add_child(Node(string[2:]))
    print(root.traverse())
