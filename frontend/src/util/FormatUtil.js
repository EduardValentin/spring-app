const formatter = new Intl.NumberFormat(undefined, { style: 'currency', currency: 'usd' });
export const formatCurrency = number => formatter.format(number);
