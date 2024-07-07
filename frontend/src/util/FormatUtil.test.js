import { formatCurrency } from './FormatUtil.js';

test('Formats currency', () => {
	expect(formatCurrency(123.4)).toEqual('$123.40');
	expect(formatCurrency(33123)).toEqual('$33,123.00');
	expect(formatCurrency(-2333123.99)).toEqual('-$2,333,123.99');
});
