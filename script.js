
const URL = 'http://localhost:4080/auth/login'; // gateway URL
const TOTAL_REQUESTS = 30;
const DELAY_MS = 100;

function sleep(ms) {
	return new Promise(r => setTimeout(r, ms));
}

(async () => {
	for (let i = 1; i <= TOTAL_REQUESTS; i++) {
		try {
			const res = await fetch(URL, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify({ username: 'test', password: 'test' })
			});

			console.log(
					`#${i}`,
					res.status,
					res.status === 429 ? '❌ RATE LIMITED' : '✅ ALLOWED',
					`at ${new Date()}`
			);
		} catch (err) {
			console.error(`#${i} ERROR`, err.message);
		}

		await sleep(DELAY_MS);
	}
})();
