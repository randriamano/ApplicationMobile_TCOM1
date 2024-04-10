/**
 * To get data from other api
 */
const fetchAPI = (apiURL) => {
  return new Promise((resolve, reject) => {
    fetch(apiURL, {
      method: 'GET',
      headers: {
        "Accept": "applicatioin/json",
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (response.ok)
          resolve(response.json())
      })
      .catch((error) => {
        reject(error)
      })
  })
}

module.exports = fetchAPI
