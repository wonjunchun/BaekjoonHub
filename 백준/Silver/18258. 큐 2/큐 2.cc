#include<iostream>
#include<queue>

using namespace std;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	queue<int> Q;
	string operation;
	int T, operand;

	cin >> T;
	for (int t = 0; t < T; t++) {
		cin >> operation;
		
		if (operation == "push") {
			cin >> operand;
			Q.push(operand);
		}
		else if (operation == "pop") {
			if (Q.empty()) {
				cout << -1 << '\n';
			}
			else {
				cout << Q.front() << '\n';
				Q.pop();
			}
		}
		else if (operation == "size") {
			cout << Q.size() << '\n';
		}
		else if (operation == "empty") {
			cout << Q.empty() << '\n';
		}
		else if (operation == "front") {
			if (Q.empty()) {
				cout << -1 << '\n';
			}
			else {
				cout << Q.front() << '\n';
			}
		}
		else if (operation == "back") {
			if (Q.empty()) {
				cout << -1 << '\n';
			}
			else {
				cout << Q.back() << '\n';
			}
		}
	}
	return 0;
}