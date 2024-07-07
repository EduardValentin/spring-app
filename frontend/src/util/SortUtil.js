let type;
let empty;
/**
 * Test if an object is 'empty'.  null, undefined, {}, [], '', NaN are all empty.
 * @param {any} obj Object to test
 * @return {boolean} empty Whether or not the object is emptry
 */
export function isEmpty(obj) {
	type = typeof obj;
	empty = false;
	if (obj === null || type === 'undefined') {
		empty = true;
	} else if (Array.isArray(obj)) {
		empty = obj.length === 0;
	} else if ((Map && obj instanceof Map) || (Set && obj instanceof Set)) {
		empty = obj.size === 0;
	} else {
		switch (type) {
			case 'number':
				empty = isNaN(obj);
				break;
			case 'function':
			case 'boolean':
			case 'symbol':
				empty = false;
				break;
			case 'string':
				empty = obj === '';
				break;
			default:
				//Empty object, e.g. {}
				empty = Object.keys(obj).length === 0;
		}
	}
	return empty;
}

/**
 * Compare a to b
 * @param  {object} a Left hand side of comparison
 * @param  {object} b Right hand side of comparison
 * @return {number}   0=equal, -1=less than, 1=greater than
 */
export const basicSortCompare = (a, b) => {
	if (isEmpty(a) && isEmpty(b)) return 0;
	if (isEmpty(a)) return -1;
	if (isEmpty(b)) return 1;
	if (a < b) return -1;
	if (a === b) return 0;
	return 1;
};

/**
 * A basic sort function that handles sorting the way we'd like to by default
 * @param  {string} field A property name containing the data to sort
 * @return {Function}     A function that can be used as a sort function.
 */
export const basicSort = field => (obja, objb) => {
	if (isEmpty(field)) {
		return basicSortCompare(obja, objb);
	}
	return basicSortCompare(obja[field], objb[field]);
};

/**
 * Return a sort that sorts on multiple fields
 * @param {array[string]} fields An array of fields to sort on
 * @return {function} A sort function
 */
export const multiSort = fields => {
	if (fields.length === 1) {
		return basicSort(fields[0]);
	}
	const funcs = fields.map(basicSort);
	return composeSort.apply(null, funcs);
};

/**
 * Takes n sort functions and returns a single sort function that applies
 * them in order.
 * @param  {array[function]} funcs
 * @return {function} A sort function
 */
export const composeSort = (...funcs) => (a, b) => {
	const sorts = funcs.map(func => func(a, b));
	return sorts.reduce((current, next) => {
		if (current === 0) {
			//If the current sort is equal, use the next sort result for
			//sorting
			return next;
		}
		return current;
	}, 0);
};

/**
 * Reverse a sort
 * @param  {function} sortFunc A sort function
 * @return {function}          A sort function that sorts in reverse order of sortFunc
 */
export const reverse = sortFunc => (a, b) => 0 - sortFunc(a, b);
