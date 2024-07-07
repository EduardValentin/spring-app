import { basicSort, composeSort, multiSort, reverse, isEmpty } from './SortUtil.js';

const list = [
	{ prop1: 4, prop2: 1, deep: { prop: 'a' }, dup: 1 },
	{ prop1: 3, prop2: 2, deep: { prop: 'c' }, dup: 1 },
	{ prop1: 2, prop2: 3, deep: { prop: 'b' }, dup: 1 },
	{ prop1: 1, deep: { prop: 'z' }, dup: 2 },
	{ prop1: 5, prop2: 4, deep: { prop: 'x' }, dup: 1 },
	{ prop1: 6, prop2: 5, deep: { prop: 't' }, dup: 2 },
	{ prop1: 10, prop2: 6, deep: { prop: 'u' }, dup: 2 }
];

test('Test basic sort with no field', () => {
	expect([3, 2, 1].sort(basicSort())).toEqual([1, 2, 3]);
});
test('Test sort with fields', () => {
	expect(list.sort(basicSort('prop1')).map(item => item.prop1)).toEqual([1, 2, 3, 4, 5, 6, 10]);
	expect(list.sort(basicSort('prop2')).map(item => (item.prop2 ? item.prop2 : null))).toEqual([
		null,
		1,
		2,
		3,
		4,
		5,
		6
	]);
});

test('Test sort composition', () => {
	let localSort = composeSort(basicSort('dup'), basicSort('prop2'));
	expect(list.sort(localSort).map(item => item.prop2)).toEqual([1, 2, 3, 4, undefined, 5, 6]);
	expect(list.sort(localSort).map(item => item.dup)).toEqual([1, 1, 1, 1, 2, 2, 2]);
});

test('Test reversing a sort', () => {
	let localSort = reverse(multiSort(['dup', 'prop1']));
	expect(list.sort(localSort).map(item => item.prop1)).toEqual([10, 6, 1, 5, 4, 3, 2]);
	expect(list.sort(localSort).map(item => item.dup)).toEqual([1, 1, 1, 1, 2, 2, 2].reverse());
});

test('Test isEmpty {} is empty', () => {
	const obj = {};
	expect(isEmpty(obj)).toEqual(true);
});

test('Test isEmpty null is empty', () => {
	expect(isEmpty(null)).toEqual(true);
});

test('Test isEmpty array with entries not empty', () => {
	const array = ['test1', 'test2', 'test3'];
	expect(isEmpty(array)).toEqual(false);
});

test('Test isEmpty empty array is empty', () => {
	const array = [];
	expect(isEmpty(array)).toEqual(true);
});

test('Test isEmpty undefined is empty', () => {
	let array;
	expect(isEmpty(array)).toEqual(true);
});

test('Test isEmpty {b: 1} is not empty', () => {
	const obj = { b: 1 };
	expect(isEmpty(obj)).toEqual(false);
});

test("Test isEmpty 'hello' is not empty", () => {
	const obj = 'hello';
	expect(isEmpty(obj)).toEqual(false);
});

test("Test isEmpty '' is empty", () => {
	const obj = '';
	expect(isEmpty(obj)).toEqual(true);
});

test('Test isEmpty NaN is empty', () => {
	const obj = Number.NaN;
	expect(isEmpty(obj)).toEqual(true);
});

test('Test isEmpty false is false', () => {
	expect(isEmpty(false)).toEqual(false);
});

test('Test isEmpty true is not empty', () => {
	expect(isEmpty(true)).toEqual(false);
});

if (Symbol) {
	test('Test Symbols are not empty', () => {
		expect(isEmpty(Symbol())).toEqual(false);
	});
}

if (Map) {
	test('Test Empty Map is empty', () => {
		const m = new Map();
		expect(isEmpty(m)).toEqual(true);
	});
	test('Test non-Empty Map is not empty', () => {
		const m = new Map();
		m.set('a', 1);
		expect(isEmpty(m)).toEqual(false);
	});
}

if (Set) {
	test('Test Empty Set is empty', () => {
		const s = new Set();
		expect(isEmpty(s)).toEqual(true);
	});

	test('Test non-Empty Set is not empty', () => {
		const s = new Set();
		s.add('a');
		expect(isEmpty(s)).toEqual(false);
	});
}
