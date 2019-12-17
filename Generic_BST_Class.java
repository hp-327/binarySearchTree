package p7_package;

public class Generic_BST_Class<GenericData extends java.lang.Comparable<GenericData>>
{
	private class BST_Node
	{
		private GenericData nodeData;
		BST_Node leftChildRef;
		BST_Node rightChildRef;
		BST_Node(GenericData inData)
		{
			nodeData = inData;
			leftChildRef = null;
			rightChildRef = null;
			
		}
		/**BST_Node (BST_Node copied)
		{
			nodeData = copied.nodeData;
			//ask about left and right child
			leftChildRef = copied.leftChildRef;
			rightChildRef = copied.rightChildRef;
		}
		*/
	}
	/**
	 * Root of BST
	 */
	private Generic_BST_Class.BST_Node	BST_Root;

	/**
	 * Default class constructor, initializes BST
	 */
	public Generic_BST_Class()
	{
		BST_Root = null;
	}
	/**
	 * Clears tree
	 */
	public void clearTree()
	{
		BST_Root = null;
	}
	/**
	 * Provides inOrder traversal action
	 * Note: Calls displayInOrderHelper
	 */
	public void displayInOrder()
	{
		displayInOrderHelper(BST_Root);
	}
	/**
	 * Provides inOrder traversal action using recursion
	 * @param localRoot- BST_Node tree root reference at the current recursion level
	 */
	private void displayInOrderHelper(Generic_BST_Class.BST_Node localRoot)
	{
		if (localRoot != null)
		{
			displayInOrderHelper(localRoot.leftChildRef);
			System.out.println(localRoot.nodeData.toString());
			displayInOrderHelper(localRoot.rightChildRef);
		}	
	}
	/**
	 * Provides postOrder traversal action
	 * Note: Calls displayPostOrderHelper
	 */
	public void displayPostOrder()
	{
		displayPostOrderHelper(BST_Root);
	}
	/**
	 * Provides postOrder traversal action using recursion
	 * @param localRoot - BST_Node tree root reference at the current recursion level
	 */
	private void displayPostOrderHelper(Generic_BST_Class.BST_Node localRoot)
	{
		if (localRoot != null)
		{
			displayPostOrderHelper(localRoot.leftChildRef);
			displayPostOrderHelper(localRoot.rightChildRef);
			System.out.println(localRoot.nodeData.toString());
		}
	}
	/**
	 * Provides preOrder traversal action
	 * Note: Calls displayPreOrderHelper
	 */
	public void displayPreOrder()
	{
		displayPreOrderHelper(BST_Root);
	}
	/**
	 * Provides preOrder traversal action using recursion
	 * @param localRoot-BST_Node tree root reference at the current recursion level
	 */
	private void displayPreOrderHelper(Generic_BST_Class.BST_Node localRoot)
	{
		if (localRoot != null)
		{
			System.out.println(localRoot.nodeData.toString());
			displayPostOrderHelper(localRoot.leftChildRef);
			displayPostOrderHelper(localRoot.rightChildRef);
		}
	}
	/**
	 * Insert method for BST
	 * Note: uses insert helper method which returns root reference
	 * Note: uses search to verify that key is not already in tree; if key is already in tree, insert is not conducted
	 * Parameters: inData - GenericData data to be added to BST
	 */
	public void insert(GenericData inData)
	{
		if (isEmpty())
		{
			BST_Root = new BST_Node(inData);
		}
		else
		{
		insertHelper(BST_Root,inData);
		}
	}
	/**
	 * Insert helper method for BST insert action
	 * Note: Recursive method returns updated local root to maintain tree linkage
	 * @param localRoot - BST_Node tree root reference at the current recursion level
	 * @param inData - GenericData item to be added to BST
	 * @return: BST_Node reference used to maintain tree linkage
	 */
	private BST_Node insertHelper(BST_Node localRoot,GenericData inData)
	{
		if (localRoot.nodeData.compareTo(inData)>0)
		{
			if (localRoot.leftChildRef != null)
			{
			insertHelper(localRoot.leftChildRef, inData);
			}
			else
			{
				localRoot.leftChildRef = new BST_Node(inData);
			}
		}
		else
		{
			if (localRoot.rightChildRef != null)
			{
			insertHelper(localRoot.rightChildRef, inData);
			}
			else
			{
				localRoot.rightChildRef = new BST_Node(inData);
			}
		}
		return localRoot;
	}
	/**
	 * Test for empty tree
	 * @return: Boolean result of test
	 */
	public boolean isEmpty()
	{
		return (BST_Root == null);
	}
	
	/**
	 * Searches tree from given node to maximum value node below it, stores data value found, and then unlinks the node
	 * @param maxParent- BST_Node reference to current node
	 * @param maxLoc- BST_Node reference to child node to be tested
	 * @return: BST_Node reference containing removed node
	 */
	private BST_Node removeFromMax(BST_Node maxParent, BST_Node maxLoc)
	{
		if(maxLoc.rightChildRef != null)
		{
			removeFromMax(maxLoc,maxLoc.rightChildRef);
		}
		maxParent.rightChildRef = maxLoc.rightChildRef;
		return maxLoc;
	}
	
	/**
	 * Removes data node from tree using given key
	 * Note: uses remove helper method
	 * Note: uses search initially to get value, if it is in tree; if value found, remove helper method is called, otherwise returns false
	 * @param inData- GenericData that includes the necessary key
	 * @return:GenericData result of remove action
	 */
	public GenericData removeItem(GenericData inData)
	{
		GenericData temp = search(inData);
		
		
	    if ( temp!= null )
	    {
	    	return (GenericData) removeItemHelper( BST_Root, inData );
	    }
	    return null;
	}
	
	/**
	 * Remove helper for BST remove action
	 * Note: Recursive method returns updated local root to maintain tree linkage
	 * Note: uses removeFromMax method
	 * @param localRoot- BST_Node tree root reference at the current recursion level
	 * @param outData - GenericData item that includes the necessary key
	 * @returnBST_Node reference result of remove helper action
	 */
	private BST_Node removeItemHelper(BST_Node localRoot,GenericData outData)
	{
		if (localRoot.leftChildRef == null)
		{ 
			localRoot = (Generic_BST_Class<GenericData>.BST_Node) localRoot.rightChildRef.nodeData;
		}
		if (localRoot.rightChildRef == null)
		{
			localRoot = (Generic_BST_Class<GenericData>.BST_Node) localRoot.leftChildRef.nodeData;
		}
		if (localRoot.nodeData.compareTo(outData) < 0)
		{
			localRoot.rightChildRef = removeItemHelper(localRoot.rightChildRef , outData);	
		}	
		if (localRoot.nodeData.compareTo(outData) > 0)
		{
			localRoot.leftChildRef = removeItemHelper(localRoot.leftChildRef , outData);
		}
		
		else
			{
			if(localRoot.leftChildRef.rightChildRef == null)
			{
				localRoot.nodeData = outData;
				localRoot.leftChildRef= localRoot.leftChildRef.leftChildRef;
			}
		else
		{
			localRoot.nodeData = removeFromMax(localRoot, localRoot.leftChildRef).nodeData;
		}
			}
		return localRoot;
	}
	/**
	 * Searches for data in BST given GenericData with necessary key
	 * @param searchData - GenericData item containing key
	 * @return: GenericData reference to found data
	 */
	public GenericData search(GenericData searchData)
	{
		 //return searchHelper(BST_Root, searchData); compare error
		return (GenericData) searchHelper(BST_Root, searchData);
		
	}
	
	/**
	 * Helper method for BST search action
	 * @param localRoot - BST_Node tree root reference at the current recursion level
	 * @param searchDatasearchData - GenericData item containing key
	 * @return:GenericData item found
	 */
	private GenericData searchHelper(BST_Node localRoot,GenericData searchData)
	{
		if (localRoot == null)
		{
			return null;
		}
		if (localRoot.nodeData.compareTo(searchData)<0)
		{
			return(searchHelper(localRoot.rightChildRef, searchData));
		}
		
		else if (localRoot.nodeData.compareTo(searchData)>0)
		{
			return(searchHelper(localRoot.leftChildRef, searchData));
		}
		else
		{
			return localRoot.nodeData;
		}
	}

}
