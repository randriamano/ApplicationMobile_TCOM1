/** 
 * Extract the data in the request body 
*/
const extractData = (request) => {
  return new Promise((resolve, reject) => {
    let data = ''

    request.on('data', (chunk) => {
      data += chunk
    })

    request.on('end', () => {
      if(data !== '') {
        resolve(data)
      }
      else {
        reject(`${request.url}: NO DATA`)
      }
    })
  })
}

module.exports = { extractData }
