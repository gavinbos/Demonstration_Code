/*
	Lab 7: abstract class inheritance
	- a set of subclasses to a string filter bas class
	Gavin Bosman, 100781902, 2021/03/13
*/
#include <iostream>
#include <string>
#include <vector>
#include <cstdlib>
#include <ctime>

using namespace std;

class Filter
{
protected:
  string _name;

public:  
  Filter(const string& name) : _name(name) {}
  virtual ~Filter() {};				//although the destructor is virtual, all subclasses use the defualt destructor
							//so redefining is unnecessary
  virtual string apply(const string& input) = 0;

  string get_name() { return _name; }
};

class Capitalize : public Filter 
{
public:					
  Capitalize() : Filter("Capitalize") {}		//constructor
  						
  string apply(const string& input) {			//function redefined due to its virtual state in the base class
    string temp = input;
    for (int i = 0; i < temp.length(); ++i) {
      temp[i] = toupper(temp[i]);			//toupper converts all lowercase letters to uppercase, and leaves
    }							//everything else alone
    return temp;
  }
    
};

class CapitalizeFirstLetter : public Filter
{
public:
  CapitalizeFirstLetter() : Filter("CapitalizeFirstLetter") {}	//constructor
  
  string apply(const string& input) {					//function inherited due to its virtual state in the base class
    string temp = input;
    int index = 0;
    
    while (!islower(temp[index]) && !isupper(temp[index])) {		//loops until the first letter is reached
      ++index;
    }
    
    if (islower(temp[index])) {					//only capitalizes if the first letter is a lowercase
      temp[index] = toupper(temp[index]);
    }
    
    return temp;
  }
  
};

class CapitalizeFirstLetterOfEveryWord : public Filter
{
public:
  CapitalizeFirstLetterOfEveryWord() : Filter("CapitalizeFirstLetterOfEveryWord") {}	//constructor
  
  string apply(const string& input) {				//function inherited due to its virtual state in base class
    string temp = input;
    int pos = 0;
    vector<string> words;
    
    for (int i = 0; i < temp.length(); ++i) {			//seperates the sentance and places individual words in a string vector
      if (isspace(temp[i])) {
        words.push_back(temp.substr(pos,(i-pos)));
        pos = i + 1;
      }
    }
    words.push_back(temp.substr(pos));
    
    int spaces = words.size() - 1;
    string _return = "";
    for (string i : words) {					//loops through the words
      if (islower(i[0])) {					//then checks if the first letter is a lowercase
        i[0] = toupper(i[0]);					//if true, then make uppercase
      }
      _return.append(i);
      if (spaces > 0) {					//appends a space after every word, save for the last 
        _return.append(" ");
        spaces - 1;
      }
    }
    
    return _return;
  }
  
};

class Obenglobish : public Filter
{
public:
  Obenglobish() : Filter("Obenglobish") {}			//constructor
  
  string apply(const string& input) {				//function inherited due to its virtual state in base class
    string temp = input;
    vector<int> vowels;
    int count = 0;
    
    for (int i = 0; i < temp.length(); ++i) {			//for-loop loops over each char in the input
      char tempchar = static_cast<char>(tolower(temp[i]));
      switch (tempchar)					//switch statement determines if char is a vowel
      {
        case 'a': 
        {
          if (count == 0) {
            vowels.push_back(i);				//if a vowel, its position is added to a vector storing vowel positions
          }
          count += 1;						//count variable allows for the rule that "ob" is only applied
          break;						//for every vowel sound in a word rather than every single vowel char
        }
        case 'e': 
        {							//for ex. said would be 'sobaid' and not 'sobaobid'
          if (count == 0) {
            vowels.push_back(i);
          }
          count += 1;
          break;
        }
        case 'i': 
        {
          if (count == 0) {
            vowels.push_back(i);
          }
          count += 1;
          break;
        }
        case 'o': 
        {
          if (count == 0) {
            vowels.push_back(i);
          }
          count += 1;
          break;
        }
        case 'u': 
        {
          if (count == 0) {
            vowels.push_back(i);
          }
          count += 1;
          break;
        }
        case 'y': 
        {
          if (count == 0) {
            vowels.push_back(i);
          }
          count += 1;
          break;
        }
        default:
        {
          count = 0;
        }
      }
    }
    
    int initsize = temp.length();				//using the difference of initial size and the size after adding each 
    for (int i : vowels) {					//"ob" , along with the vowel sound positions, the ob's are added to the 
      int sizediff = temp.length() - initsize;		//input
      temp.insert(i+sizediff, "ob");
    }
    
    return temp;
  }
  
};

class ReverseString : public Filter
{
public:
  ReverseString() : Filter("ReverseString") {}		//constructor
  
  string apply(const string& input) {				//function inherited due to its virtual state in base class
    string temp = input;
    string _return;
    
    for (int i = temp.length(); i >= 0; --i) {		//iterates backwards through the input, adding the chars to a 
      _return += temp[i];					//seperate string before returning it
    }
    return _return;
  }
};

class ToSmall : public Filter
{
public:
  ToSmall() : Filter("ToSmall") {}			//constructor
  
  string apply(const string& input) {			//function inherited due to its virtual state in base class
    string temp = input;
    for (int i = 0; i < temp.length(); ++i) {		//similar to Capitalize() but utilizes tolower() rather than toupper()
      temp[i] = tolower(temp[i]);
    }
    return temp;
  }
};

class Randomize : public Filter
{
public:
  Randomize() : Filter("Randomize") {}		//constructor
  
  string apply(const string& input) {			//function inherited due to its virtual state in base class
    string temp = input;
    string _return;
    vector<char> output;
    bool validpos = false;
    srand(time(NULL));
    
    for (int i = 0; i < temp.length(); ++i) {		//sets the output vector (in reference to the input string) to newline 
      if (isspace(temp[i])) {				//characters where there are non-space chars, and spaces where there is whitespace
        output.push_back(' ');
      } else { output.push_back('\n'); }
    }
    
    for (int i = 0; i < temp.length(); ++i) {		
      if (!isspace(temp[i])) {			//looping through the chars of the input, only randomizes position if non-space char.
        while (!validpos) {				//valid position is determined by the locations of the newline characters in the 
          int randnum = rand() % temp.length();	//output vector, all other characters are left as is
          if (output.at(randnum) == '\n') {
            output.at(randnum) = temp[i];		//places current char at random position in output vec
            validpos = true;
          }
        }
        validpos = false;
      }
    }
    
    for (char i : output) {				//adds the chars from the output vec (randomized input) to a return string
      _return += i;
    }
    
    return _return;
  }
};


int main() {

  const int num_filters = 7;
  Filter* filters[num_filters];

  filters[0] = new Capitalize();
  filters[1] = new CapitalizeFirstLetter();
  filters[2] = new CapitalizeFirstLetterOfEveryWord();
  filters[3] = new Obenglobish();
  filters[4] = new ReverseString();
  filters[5] = new ToSmall();
  filters[6] = new Randomize();
 
  string input;
  cout << "Enter string: ";  getline(cin, input);
  
  cout << "Input string: " << input << endl; 
  
  for (int i=0; i<num_filters; ++i) {
    cout << "Filter name:  " << filters[i]->get_name() << endl;
    cout << "Output:       " << filters[i]->apply(input) << endl; 
  }
 
  for (int i=0; i<num_filters; ++i) delete filters[i];
  return 0;
}