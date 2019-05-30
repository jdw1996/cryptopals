/*
 * Joseph Winters
 * Utility functions for base-64 encoding and decoding
 * August 2018
 */

#include <stdint.h>

// Return the length of a base-64 encoded string given the number of bytes to
// encode.
int get_encoded_length(int decoded_length);

// Return the number of bytes encoded by a base-64 encoded string with a given
// length and amount of padding.
int get_decoded_length(int encoded_length, int padding_used);

// Return a base-64 encoded version of data.
char *b64_encode(const uint8_t *data);

// Return a decoded version of the base-64 encoded string data.
uint8_t *b64_decode(const char *data);

// Free the memory from an array returned by b64_encode or b64_decode.
void b64_free(void *arr);
