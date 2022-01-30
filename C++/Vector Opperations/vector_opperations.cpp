#include <iostream>
#include <vector>
using namespace std;

int main() {
	vector<int> v1, v2;
	
	//Add values to the vector using push_back
	//push_back - adds an element to the end
	for (int i  = 1; i <= 10; i++) {
		v1.push_back(i);
	}
	
	//assigns values to the vector
	//begin - returns an iterator to the beginning
	//end - iterator to the end
	//c++11 -  cbegin and cend as extensions
	v2.assign(v1.begin(), v1.end()); //v2 = v1
	
	//c++11 iterate and display the values in the vector
	//notice that v2 contains the contents of v1
	for (int i : v2) { //newer convention for iterating vector
		cout << i << endl;
	}
	
	//at gets the specified element with bounds checking
	cout << "AT: " << v1.at(9) << endl;
	
	//can also use []. similar to at, but does not have bounds checking
	cout << "[9]: " << v1[9] << endl;
	
	//front, returns the first element
	//back, returns the last element
	cout << "front " << v1.front() << endl;
	cout << "back " << v1.back() << endl;
	
	//data, which provides access to the underlying array
	cout << "data: " << v1.data() << endl;
	
	//forward iterator, goes from start to end (1....10)
	//rbegin and rend are used for the reverse iterator
	//c++11 crbegin and crend
	//c++ auto keyword used, simplifies statement for defining it type
	
	//reverse iterator
	/*
	for (auto it = v1.rbegin(); it = v1.rend(); ++it) {
		cout << *it << endl;
	}
	*/
	
	// capacity operations
	// empty - check if the container is empty
	// size - the size of it
	// max_size -  the maximum possible number of elements
	cout << "empty: " << v1.empty() << endl;
	cout << "size: " << v1.size() << endl;
	cout << "max_size: " << v1.max_size() << endl;
	
	// capacity - the number of elements that can be held in the currently alocated storage
	// reserve - reserves storage
	cout << "capacity: " << v1.capacity() << endl;
	v1.reserve(100);
	cout << "capacity after reserve(100): " << endl;
	
	//modifiers
	//clear - clears the contents by erasing all elements
	//erase - erases the selected specified elements
	vector<int> v3 = {1, 2, 3, 4, 5};
	cout << "v3 before: ";
	for (int i: v3) cout << i << endl;
	v3.erase(v3.begin()+3, v3.end());
	cout << "erase elements: ";
	for (int i: v3) cout << i << endl;
	
	v3.clear();
	cout << "clear: ";
	for(int i : v3) cout << i << endl;
	
	//insert - inserts elements
	//pop_back - removes the last element
	cout << "v2 before: ";
	for (int i: v2) cout << i << endl;
	
	v2.insert(v2.begin(), -1);
	v2.insert(v2.end(), 100);
	
	cout << "v2 after: ";
	for (int i: v2) cout << i << endl;
	
	v2.pop_back();
	cout << "after pop_back: " << v2.back() << endl;
	
	// resize - change num of elements stored
	// swap - swap a and b
	vector<int> a = {1,2,3};
	vector<int> b = {4,5,6};
	a.swap(b);
	
	cout << "after swap A: ";
	for (int i: a) cout << i << endl;
	
	cout << "after swap B: ";
	for (int i: b) cout << i << endl;
	
	return 0;
}
