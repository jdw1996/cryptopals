/*
 * Joseph Winters
 * Base-64 implementation
 * August 2018
 */

#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#include "base64.h"

/* HELPER FUNCTIONS */

// Given a number in the range [0,63], return the corresponding base-64 digit.
char b64_value_encode(char n) {
	if (0 <= n && n <= 25) {
		return 'A' + n;
	} else if (26 <= n && n <= 51) {
		return 'a' - 26 + n;
	} else if (52 <= n && n <= 61) {
		return '0' - 52 + n;
	} else if (n == 62) {
		return '+';
	} else {  // n == 63
		return '/';
	}
}

// Given a base-64 digit, return the corresponding numerical value.
char b64_value_decode(char n) {
	if ('A' <= n && n <= 'Z') {
		return n - 'A';
	} else if ('a' <= n && n <= 'z') {
		return n - 'a' + 26;
	} else if ('0' <= n && n <= '9') {
		return n - '0' + 52;
	} else if (n == '+') {
		return 62;
	} else {  // n == '/'
		return 63;
	}
}

/* EXPORTED FUNCTIONS */

// See header file.
char *b64_encode(const char *data) {
	// Find the expected length of the encoded string, allocate memory for it.
	int len = strlen(data);
	const int encoded_len = 4 * (len / 3 + (len % 3 != 0));  // 4 * ceil(len / 3)
	char *ret = malloc((encoded_len + 1) * sizeof(*ret));
	if (ret == NULL) return ret;

	// Find the number of '=' required at end of encoded string.
	int padding_needed;
	if (len % 3 == 0) {
		padding_needed = 0;
	} else {
		padding_needed = 3 - (len % 3);
	}

	// Handle each chunk, taking padding into account.
	int num_iterations = encoded_len/4;
	for (int i = 0; i < num_iterations; i++) {
		// Determine if this is the last iteration.
		bool is_last_iteration = (i == num_iterations - 1);
		// Retrieve the characters to encode.
		char chunk_char0 = data[3 * i];
		char chunk_char1 =
			padding_needed == 2 && is_last_iteration ?
			'\0' :
			data[3 * i + 1];
		char chunk_char2 =
			padding_needed >= 1 && is_last_iteration ?
			'\0' :
			data[3 * i + 2];
		// Set the encoding characters.
		ret[4 * i] = b64_value_encode(chunk_char0 >> 2);
		ret[4 * i + 1] = b64_value_encode(((chunk_char0 & 0x03) << 4) | (chunk_char1 >> 4));
		ret[4 * i + 2] =
			padding_needed == 2 && is_last_iteration ?
			'=' :
			b64_value_encode(((chunk_char1 & 0x0f) << 2) | (chunk_char2 >> 6));
		ret[4 * i + 3] =
			padding_needed >= 1 && is_last_iteration ?
			'=' :
			b64_value_encode(chunk_char2 & 0x3f);
	}

	// Add null terminator.
	ret[encoded_len] = '\0';

	return ret;
}

// See header file.
char *b64_decode(const char *data) {
	// Find the expected length of the decoded string, allocate memory for it.
	int len = strlen(data);
	int padding_used = (data[len - 2] == '=') + (data[len - 1] == '=');
	const int decoded_len = 3 * len/4 - padding_used;
	char *ret = malloc((decoded_len + 1) * sizeof(*ret));
	if (ret == NULL) return ret;

	// Decode one chunk at a time.
	for (int i = 0; i < len/4; i++) {
		// Determine if this is the last iteration.
		bool is_last_iteration = (i == len/4 - 1);
		// Retrieve the characters to decode.
		char chunk_char0 = b64_value_decode(data[4 * i]);
		char chunk_char1 = b64_value_decode(data[4 * i + 1]);
		char chunk_char2 =
			padding_used == 2 && is_last_iteration ?
			'\0' :
			b64_value_decode(data[4 * i + 2]);
		char chunk_char3 =
			padding_used >= 1 && is_last_iteration ?
			'\0' :
			b64_value_decode(data[4 * i + 3]);
		// Get the decoded characters.
		ret[3 * i] = (chunk_char0 << 2) | (chunk_char1 >> 4);
		if (!is_last_iteration || padding_used <= 1)
			ret[3 * i + 1] = (chunk_char1 << 4) | (chunk_char2 >> 2);
		if (!is_last_iteration || padding_used == 0)
			ret[3 * i + 2] = (chunk_char2 << 6) | chunk_char3;
	}

	// Add null terminator.
	ret[decoded_len] = '\0';

	return ret;
}

// See header file.
void b64_free(char *data) {
	free(data);
}
