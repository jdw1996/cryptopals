/*
 * Joseph Winters
 * Utility functions for base-64 encoding and decoding
 * August 2018
 */

#include <stdint.h>

// Return a base-64 encoded version of data.
char *b64_encode(const uint8_t *data);

// Return a decoded version of the base-64 encoded string data.
uint8_t *b64_decode(const char *data);

// Free the memory from an array returned by b64_encode or b64_decode.
void b64_free(void *arr);
