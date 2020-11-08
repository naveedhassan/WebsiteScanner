# WebsiteScanner
This app scans through a webpage and displays the top 10 frequent words and the top 10 frequent word pairs. When it encounters a hyperlink, it recursively scans the webpage and for frequent word and word pairs. The Maximum depth of recursion can be changed, currently its 4.

This App uses jsoup as third party libarary to parse html objects and read through the webpage. The app also utilizes jsoup utility to fetch all the hyperlinks in the webpage.

The App first fetches all the words from the URL provided as input. Then the App fetches all hyper links in the webpage. It then recursively reads words from webpages scanning the hypperling. This goes uptil fourth Level.

To fetch Most frequent used words there were two options for data structure selection. HashMap and Trie. We chose to use tries due to memory overhead that hasmap would have caused. Hahmap would not be an optimal choice as the number of words increases and when we have to deal huge data set.

Trie is an efficient information reTrieval data structure. Using Trie, search complexities can be brought to optimal limit (key length). If we store keys in binary search tree, a well balanced BST will need time proportional to M * log N, where M is maximum string length and N is number of keys in tree.

To find the Maximum k elements in a tree, we traverse the trie in a preorder way and add the entry(key,count) in the MaxHeap. We are using Java collections Priority Queue to achieve the Max heap. Once the traversing is done, we poll the first 10 elemts from the Max Priority queue.

Sample Data that is fetched from 314e.com

[[WordCount [word=the, count=774], WordCount [word=to, count=672], WordCount [word=and, count=636], WordCount [word=ehr, count=528], WordCount [word=of, count=527], WordCount [word=data, count=489], WordCount [word=services, count=476], WordCount [word=it, count=455], WordCount [word=a, count=379], WordCount [word=for, count=378]], [WordCount [word=healthcare-it, count=175], WordCount [word=cures-act, count=124], WordCount [word=ehr-services, count=105], WordCount [word=staff-augmentation, count=98], WordCount [word=ehr-interfaces, count=93], WordCount [word=2020-314e, count=93], WordCount [word=data-conversion, count=91], WordCount [word=cloud-adoption, count=90], WordCount [word=services-ehr, count=90], WordCount [word=data-analytics, count=82]]]

#How to Run the code 

  app.webScanner.webScanner is the entry point for the APP.

