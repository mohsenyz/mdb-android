# MDB - Android orm database library

Basic useful feature list:

 * very small library
 * can be uses in all api versions
 * fast and easy
 * File contents are saved in the URL so you can share files

MDB is an android orm database library like sugarorm

I create this library just to train java reflection api
hope it will be usefull for you too

How to use (step by step):
```java
public class Books extends MDBRecord<Books>{
	public String name;
    public long price;
}
```
And when your app start first time:
```java
	MDB.init(Books.class);
```

Above code will make a sqlite table
it is more fast than making sql for creating table by hand

And then you can :
```java
Books book = new Books();
book.name = "the man who smiles";
book.price = 1000;
//to save as a new field in database
int id = book.save();//return unique id of inserted field
```
And for updating row:
```java
Books book = new Books().findById(id);
book.name = "the woman who smiles";
book.save();//it will just update the row
```

for deleting row:
```java
book.delete();
```
### Thanks:

 * sugarorm library
